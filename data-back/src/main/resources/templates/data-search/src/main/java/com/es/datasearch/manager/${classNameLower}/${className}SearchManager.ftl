package ${package}.manager.${classNameLower};


import com.alibaba.fastjson.JSON;
import ${package}.param.Query${className}SearchVO;
import ${package}.result.ResultByEsDO;
import ${package}.util.ConvertDateUtil;
import com.es.stone.constant.EsConstant;
import com.es.stone.enums.EsStatus;
import com.es.stone.manager.ElasticSearchInitClientManager;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.search.*;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.geo.GeoPoint;
import org.elasticsearch.common.unit.Fuzziness;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.Operator;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.FieldSortBuilder;
import org.elasticsearch.search.sort.GeoDistanceSortBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.elasticsearch.index.query.QueryBuilders.termQuery;

/** 
 * Description: [${table.tableDesc}从ES查询数据]
 * Created on ${date}
 * @author  <a href="mailto: ${email}">${author}</a>
 * @version 1.0 
 * Copyright (c) ${year} ${website}
 */
@Slf4j
@Component
public class ${className}SearchManager {

	@Autowired
	private ElasticSearchInitClientManager elasticSearchInitClientManager;

	/**
	* ${className}列表信息列表查询
	*
	* @param query${className}SearchVO
	* @return
	*/
	public ResultByEsDO query${className}List(Query${className}SearchVO query${className}SearchVO) {
		log.info("${className}SearchManager.query${className}List.query${className}SearchVO:{}", JSON.toJSONString(query${className}SearchVO));

		ResultByEsDO resultByEsDO = ResultByEsDO.builder().build();
		List<Map> ${classNameLower}List = new ArrayList<>();//存放文章列表信息
		// 限制最多查询50条
		if (query${className}SearchVO.pageSize < 1 || query${className}SearchVO.pageSize > 50) {
			resultByEsDO.builder()
				.recordSize(0)
				.esStatus(EsStatus.PARAM_ERROR.getDesc())
				.build();
			return resultByEsDO;
		}
		RestHighLevelClient client = elasticSearchInitClientManager.getElasticClient();
		SearchRequest searchRequest = new SearchRequest("${dbName}.${tableName}");
		searchRequest.types(EsConstant.EsIndexProperty.GENERAL_TYPE);

		SearchSourceBuilder searchSourceBuilder = builder${className}SquareCondition(query${className}SearchVO);//组装查询条件

		searchRequest.source(searchSourceBuilder);
		//查询条件组装完成-------------------------------------------------------------------------------------------------
		try {
			SearchResponse rsp = client.search(searchRequest);//开始查询
			SearchHits hits = rsp.getHits();

			for (SearchHit hit : hits) {
				Map recordMap = hit.getSourceAsMap();
				ConvertDateUtil.convertDate(recordMap);
				${classNameLower}List.add(recordMap);
			}
		} catch (Exception e) {
			log.error("query${className}List search from tbArticle error!" + searchRequest.toString(), e);
		}
		return resultByEsDO.builder()
			.recordSize(${classNameLower}List.size())
			.list(${classNameLower}List)
			.pageNo(query${className}SearchVO.pageNo)
			.pageSize(query${className}SearchVO.pageSize)
			.esStatus(EsStatus.SUCESS.getDesc())
			.build();

	}

	/**
	* 组装${className}查询逻辑
	*
	* @param query${className}SearchVO
	* @return
	*/
	protected static SearchSourceBuilder builder${className}SquareCondition(Query${className}SearchVO query${className}SearchVO) {
		//组装查询条件-----------------------------------------------------------------------------
		SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
		if (query${className}SearchVO.pageNo > 0) {
			int from = (query${className}SearchVO.pageNo - 1) * query${className}SearchVO.pageSize;
			int size = query${className}SearchVO.pageSize;
			searchSourceBuilder.from(from);//设置分页
			searchSourceBuilder.size(size);
		}

		BoolQueryBuilder bq = QueryBuilders.boolQuery();

		if (!StringUtils.isEmpty(query${className}SearchVO.keyWords)) {
			//设置是否关键字查询条件，并增加了ik分词器，字段的分词器也需要在建索引时进行设置
			bq.must(QueryBuilders.multiMatchQuery(query${className}SearchVO.keyWords, "title", "content").analyzer("ik_smart").operator(Operator.AND).fuzziness(Fuzziness.AUTO));
		}
		if (queryTbUserSearchVO.id != null && query${className}SearchVO.id > 0) {
			//设置是否主键id查询条件
			bq.must(termQuery("id", query${className}SearchVO.id));
		}
		if (query${className}SearchVO.createStartTime != null) {
			bq.filter(QueryBuilders.rangeQuery("create_time").format("yyyy-MM-dd").gte(query${className}SearchVO.createStartTime).timeZone("Asia/Shanghai"));
		}
		if (query${className}SearchVO.createEndTime != null) {
			bq.filter(QueryBuilders.rangeQuery("create_time").format("yyyy-MM-dd").lte(query${className}SearchVO.createEndTime).timeZone("Asia/Shanghai"));
		}
		searchSourceBuilder.query(bq);

		if (query${className}SearchVO.sortType == 1) {
			//当排序规则传入为1时，按照距离正序排序
			searchSourceBuilder.sort(new GeoDistanceSortBuilder("location",
				new GeoPoint().parseFromLatLon(query${className}SearchVO.latitude + "," + query${className}SearchVO.longitude))
				.order(SortOrder.ASC));
		} else if (query${className}SearchVO.sortType == 0) {
			//当排序规则传入为0时，按照时间倒序排序
			searchSourceBuilder.sort(new FieldSortBuilder("create_time").order(SortOrder.DESC));
		}

		return searchSourceBuilder;
	}

}

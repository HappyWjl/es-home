package com.es.datasearch.manager.tbArticle;

import com.alibaba.fastjson.JSON;
import com.es.datasearch.param.QueryArticleSearchVO;
import com.es.datasearch.result.ResultByEsDO;
import com.es.stone.converter.ArrayConverter;
import com.es.datasearch.util.ConvertDateUtil;
import com.es.stone.constant.EsConstant;
import com.es.stone.enums.EsStatus;
import com.es.stone.manager.ElasticSearchInitClientManager;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.search.*;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.geo.GeoPoint;
import org.elasticsearch.common.unit.Fuzziness;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.Operator;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.Scroll;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.FieldSortBuilder;
import org.elasticsearch.search.sort.GeoDistanceSortBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.elasticsearch.index.query.QueryBuilders.matchQuery;
import static org.elasticsearch.index.query.QueryBuilders.termQuery;

@Slf4j
@Component
public class ArticleSearchManager {

    @Autowired
    private ElasticSearchInitClientManager elasticSearchInitClientManager;

    /**
     * article列表信息列表查询
     *
     * @param queryArticleSearchVO
     * @return
     */
    public ResultByEsDO queryArticleList(QueryArticleSearchVO queryArticleSearchVO) {
        log.info("ArticleSearchManager.queryArticleList.queryArticleSearchVO:{}", JSON.toJSONString(queryArticleSearchVO));

        ResultByEsDO resultByEsDO = ResultByEsDO.builder().build();
        List<Map> articleList = new ArrayList<>();//存放文章列表信息
        // 限制最多查询50条
        if (queryArticleSearchVO.pageSize < 1 || queryArticleSearchVO.pageSize > 50) {
            resultByEsDO.builder()
                    .recordSize(0)
                    .esStatus(EsStatus.PARAM_ERROR.getDesc())
                    .build();

            return resultByEsDO;
        }
        RestHighLevelClient client = elasticSearchInitClientManager.getElasticClient();
        SearchRequest searchRequest = new SearchRequest(EsConstant.EsIndexName.DB_SEARCH_TB_ARTICLE);
        searchRequest.types(EsConstant.EsIndexProperty.GENERAL_TYPE);

        SearchSourceBuilder searchSourceBuilder = builderArticleSquareCondition(queryArticleSearchVO);//组装查询条件

        searchRequest.source(searchSourceBuilder);
        //查询条件组装完成-------------------------------------------------------------------------------------------------
        try {
//            articleList = searchByScroll(client);
            SearchResponse rsp = client.search(searchRequest);//开始查询
            SearchHits hits = rsp.getHits();

            for (SearchHit hit : hits) {
                Map recordMap = hit.getSourceAsMap();
                ConvertDateUtil.convertDate(recordMap);
                articleList.add(recordMap);
            }
        } catch (Exception e) {
            log.error("queryArticleList search from tbArticle error!" + searchRequest.toString(), e);
        }
        return resultByEsDO.builder()
                .recordSize(articleList.size())
                .list(articleList)
                .pageNo(queryArticleSearchVO.pageNo)
                .pageSize(queryArticleSearchVO.pageSize)
                .esStatus(EsStatus.SUCESS.getDesc())
                .build();

    }

    /**
     * 组装文章查询逻辑
     *
     * @param queryArticleSearchVO
     * @return
     */
    protected static SearchSourceBuilder builderArticleSquareCondition(QueryArticleSearchVO queryArticleSearchVO) {
        //组装查询条件-----------------------------------------------------------------------------
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        if (queryArticleSearchVO.pageNo > 0) {
            int from = (queryArticleSearchVO.pageNo - 1) * queryArticleSearchVO.pageSize;
            int size = queryArticleSearchVO.pageSize;
            searchSourceBuilder.from(from);//设置分页
            searchSourceBuilder.size(size);
        }

        BoolQueryBuilder bq = QueryBuilders.boolQuery();

        if (!StringUtils.isEmpty(queryArticleSearchVO.keyWords)) {
            //设置是否关键字查询条件，并增加了ik分词器，字段的分词器也需要在建索引时进行设置
            bq.must(QueryBuilders.multiMatchQuery(queryArticleSearchVO.keyWords, "title", "content").analyzer("ik_smart").operator(Operator.AND).fuzziness(Fuzziness.AUTO));
        }
        if (queryArticleSearchVO.id > 0) {
            //设置是否主键id查询条件
            bq.must(termQuery("id", queryArticleSearchVO.id));
        }
        if (!StringUtils.isEmpty(queryArticleSearchVO.title)) {
            //设置是否文章标题查询条件，并增加了ik分词器，字段的分词器也需要在建索引时进行设置
            bq.must(matchQuery("title", queryArticleSearchVO.title).analyzer("ik_smart").operator(Operator.AND).fuzziness(Fuzziness.AUTO));
        }
        if (!StringUtils.isEmpty(queryArticleSearchVO.content)) {
            //设置是否文章内容查询条件，并增加了ik分词器，字段的分词器也需要在建索引时进行设置
            bq.must(matchQuery("content", queryArticleSearchVO.content).analyzer("ik_smart").operator(Operator.AND).fuzziness(Fuzziness.AUTO));
        }
        if (!StringUtils.isEmpty(queryArticleSearchVO.state)) {
            //设置是否文章状态查询条件，类似SQL中的 select * from tb_article where state in (1,2);
            //queryArticleSearchVO.state 需要转成int数组
            bq.must(QueryBuilders.termsQuery("state", ArrayConverter.convertArray(queryArticleSearchVO.state)));
        }
        if (queryArticleSearchVO.createStartTime != null) {
            bq.filter(QueryBuilders.rangeQuery("create_time").format("yyyy-MM-dd").gte(queryArticleSearchVO.createStartTime).timeZone("Asia/Shanghai"));
        }
        if (queryArticleSearchVO.createEndTime != null) {
            bq.filter(QueryBuilders.rangeQuery("create_time").format("yyyy-MM-dd").lte(queryArticleSearchVO.createEndTime).timeZone("Asia/Shanghai"));
        }
        searchSourceBuilder.query(bq);

        if (queryArticleSearchVO.sortType == 1) {
            //当排序规则传入为1时，按照距离正序排序
            searchSourceBuilder.sort(new GeoDistanceSortBuilder("location",
                    new GeoPoint().parseFromLatLon(queryArticleSearchVO.latitude + "," + queryArticleSearchVO.longitude))
                    .order(SortOrder.ASC));
        } else if (queryArticleSearchVO.sortType == 0) {
            //当排序规则传入为0时，按照时间倒序排序
            searchSourceBuilder.sort(new FieldSortBuilder("create_time").order(SortOrder.DESC));//按文章创建时间倒序排列。
        }
        return searchSourceBuilder;
    }

    //scroll 查询例子
    //深分页查询全部数据，量级过亿，再研究就可以。
    private List<Map> searchByScroll(RestHighLevelClient client) {
        List<Map> list = new ArrayList<>();

        SearchRequest searchRequest = new SearchRequest(EsConstant.EsIndexName.DB_SEARCH_TB_ARTICLE);
        searchRequest.types(EsConstant.EsIndexProperty.GENERAL_TYPE);
        searchRequest.scroll(new TimeValue(60000));

        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.size(10);
        searchRequest.source(searchSourceBuilder);

        SearchResponse scrollResp = null;
        try {
            scrollResp = client.search(searchRequest);
        } catch (IOException e) {
            e.printStackTrace();
        }
        final Scroll scroll = new Scroll(TimeValue.timeValueMinutes(1L));
        do {
            for (SearchHit hit : scrollResp.getHits().getHits()) {

                Map recordMap = hit.getSourceAsMap();
                try {
                    ConvertDateUtil.convertDate(recordMap);
                    list.add(recordMap);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
            SearchScrollRequest scrollRequest = new SearchScrollRequest(scrollResp.getScrollId());
            scrollRequest.scroll(scroll);
            try {
                scrollResp = client.searchScroll(scrollRequest);
            } catch (IOException e) {
                e.printStackTrace();
            }

        } while(scrollResp.getHits().getHits().length != 0);

        return list;
    }

}

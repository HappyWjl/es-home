package com.es.datadump.helper;

import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;

import java.util.List;

public class ArticleCollectHelper {
	/**
	 * 组装根据Id查询文章信息的查询逻辑
	 * @param ids
	 * @return
	 */
	public static SearchRequest buildArticleSearchRequestById(List<Long> ids) {
		SearchRequest searchRequest = new SearchRequest("db_search.tb_article");
		searchRequest.types("doc");
		SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
		searchSourceBuilder.query(QueryBuilders.termsQuery("_id", ids));
		return searchRequest.source(searchSourceBuilder);
	}
}

package com.es.datadump.manager.article;

import com.es.datadump.helper.ArticleCollectHelper;
import com.es.stone.manager.ElasticSearchInitClientManager;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class ArticleCollectManager {

    @Autowired
    private ElasticSearchInitClientManager elasticSearchInitClientManager;

    /**
     * 根据Id查询Article信息
     *
     * @param ids
     * @return
     */
    public List<Map> getUserCollectByPlaceIds(List<Long> ids) {
        List<Map> userCollectList = null;
        RestHighLevelClient client = elasticSearchInitClientManager.getElasticClient();
        //组装查询逻辑
        SearchRequest searchRequest = ArticleCollectHelper.buildArticleSearchRequestById(ids);
        try {
            SearchResponse rsp = client.search(searchRequest);
            SearchHits hits = rsp.getHits();
            if (hits.totalHits > 0) {
                userCollectList = new ArrayList<>();
            }
            for (SearchHit hit : rsp.getHits()) {
                userCollectList.add(hit.getSourceAsMap());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return userCollectList;
    }

    /**
     * 根据Id查询Article信息
     *
     * @param id
     * @return
     */
    public Map getUserCollectByPlaceId(Long id) {
        List<Map> maps = getUserCollectByPlaceIds(Arrays.asList(id));
        return null == maps ? null : maps.get(0);
    }

}
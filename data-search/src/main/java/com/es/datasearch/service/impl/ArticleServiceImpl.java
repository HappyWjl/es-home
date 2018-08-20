package com.es.datasearch.service.impl;

import com.alibaba.fastjson.JSON;
import com.es.datasearch.manager.elasticsearch.ArticleSearchManager;
import com.es.datasearch.param.QueryArticleSearchVO;
import com.es.datasearch.result.ArticleResultByEsDO;
import com.es.datasearch.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;

public class ArticleServiceImpl implements ArticleService {

    @Autowired
    ArticleSearchManager articleSearchManager;

    @Override
    public ArticleResultByEsDO getArticleList(QueryArticleSearchVO queryArticleSearchVO) {
        String result = articleSearchManager.queryArticleList(queryArticleSearchVO);
        ArticleResultByEsDO articleResultByEsDO = JSON.parseObject(result, ArticleResultByEsDO.class);
        return articleResultByEsDO;
    }

}

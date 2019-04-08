package com.es.datasearch.service.impl;

import com.es.datasearch.manager.elasticsearch.ArticleSearchManager;
import com.es.datasearch.param.QueryArticleSearchVO;
import com.es.datasearch.result.ResultByEsDO;
import com.es.datasearch.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    ArticleSearchManager articleSearchManager;

    @Override
    public ResultByEsDO getArticleList(QueryArticleSearchVO queryArticleSearchVO) {
        return articleSearchManager.queryArticleList(queryArticleSearchVO);
    }

}

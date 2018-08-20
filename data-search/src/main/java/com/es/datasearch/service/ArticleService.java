package com.es.datasearch.service;

import com.es.datasearch.param.QueryArticleSearchVO;
import com.es.datasearch.result.ArticleResultByEsDO;

public interface ArticleService {

    ArticleResultByEsDO getArticleList(QueryArticleSearchVO queryArticleSearchVO);

}

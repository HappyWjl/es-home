package com.es.datasearch.service;

import com.es.datasearch.param.QueryArticleSearchVO;
import com.es.datasearch.result.ResultByEsDO;

public interface ArticleService {

    ResultByEsDO getArticleList(QueryArticleSearchVO queryArticleSearchVO);

}

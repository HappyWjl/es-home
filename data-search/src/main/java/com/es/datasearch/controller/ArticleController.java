
package com.es.datasearch.controller;

import com.es.datasearch.param.QueryArticleSearchVO;
import com.es.datasearch.result.ArticleResultByEsDO;
import com.es.datasearch.service.ArticleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 文章搜索相关
 *
 */
@RestController
@RequestMapping("/api/article")
public class ArticleController {

	private static final Logger logger = LoggerFactory.getLogger(ArticleController.class);

    @Autowired
    private ArticleService articleService;

    /**
     * 查询文章列表
     * @return
     */
    @PostMapping("/getArticleList")
    public ArticleResultByEsDO getArticleList(@RequestBody QueryArticleSearchVO queryArticleSearchVO) {
        ArticleResultByEsDO articleList = articleService.getArticleList(queryArticleSearchVO);
        return articleList;
    }
    
}

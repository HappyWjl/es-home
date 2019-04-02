
package com.es.datasearch.controller;

import com.es.datasearch.param.QueryArticleSearchVO;
import com.es.datasearch.result.ResultByEsDO;
import com.es.datasearch.service.ArticleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 文章搜索相关
 *
 */
@Slf4j
@RestController
@RequestMapping("/api/article")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    /**
     * 查询文章列表
     * @return
     */
    @PostMapping("/getArticleList")
    public ResultByEsDO getArticleList(@RequestBody QueryArticleSearchVO queryArticleSearchVO) {
        ResultByEsDO articleList = articleService.getArticleList(queryArticleSearchVO);
        return articleList;
    }
    
}

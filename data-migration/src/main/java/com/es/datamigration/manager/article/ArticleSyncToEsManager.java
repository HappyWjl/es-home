package com.es.datamigration.manager.article;

import com.es.datamigration.manager.ServiceImportManager;
import com.es.datamigration.mapper.TbArticleDOMapper;
import com.es.datamigration.model.TbArticleDO;
import com.es.datamigration.util.ConvertUserUtil;
import com.es.stone.constant.EsConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

public class ArticleSyncToEsManager {

    private final static Logger logger = LoggerFactory.getLogger(ArticleSyncToEsManager.class);

    @Autowired
    private ServiceImportManager serviceImportManager;

    @Autowired
    private TbArticleDOMapper tbArticleDOMapper;

    /**
     * 同步数据到ES主控方法
     */
    public String syncDataControl() {
        List<TbArticleDO> articleDOList = tbArticleDOMapper.selectAll();//查询所有文章列表

        for (TbArticleDO tbArticleDO : articleDOList) {
            Map colMap = ConvertUserUtil.convertToMap(tbArticleDO);
            colMap.put(EsConstant.ES_KEY, colMap.get("id").toString());
            try {
                serviceImportManager.getDateMap(colMap, "db_search.tb_article");
            } catch (Exception e) {
                logger.error("同步数据异常，ID: {} , e: {}", tbArticleDO.getId(), e);
            }
        }
        return "success";//执行成功返回结果
    }

}

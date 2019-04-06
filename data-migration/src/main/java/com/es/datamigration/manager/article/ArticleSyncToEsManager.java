package com.es.datamigration.manager.article;

import com.es.datamigration.manager.ServiceImportManager;
import com.es.datamigration.mapper.TbArticleDOMapper;
import com.es.datamigration.model.TbArticleDO;
import com.es.datamigration.util.ConvertMapUtil;
import com.es.stone.constant.EsConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

@Slf4j
public class ArticleSyncToEsManager {

    @Autowired
    private ServiceImportManager serviceImportManager;

    @Autowired
    private TbArticleDOMapper tbArticleDOMapper;

    /**
     * 同步数据到ES主控方法
     */
    public String syncDataControl() {
        List<TbArticleDO> articleDOList = tbArticleDOMapper.selectAll();

        for (TbArticleDO tbArticleDO : articleDOList) {
            Map colMap = ConvertMapUtil.convertToMap(tbArticleDO);
            colMap.put(EsConstant.ES_KEY, colMap.get(EsConstant.ID).toString());
            try {
                serviceImportManager.getDateMap(colMap, EsConstant.EsIndexName.DB_SEARCH_TB_ARTICLE);
            } catch (Exception e) {
                log.error("同步数据异常，ID: {} , e: {}", tbArticleDO.getId(), e);
            }
        }
        return "success";
    }

}

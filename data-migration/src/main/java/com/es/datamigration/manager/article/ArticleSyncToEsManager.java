package com.es.datamigration.manager.article;

import com.es.datamigration.manager.ServiceImportManager;
import com.es.datamigration.mapper.TbArticleDOMapper;
import com.es.datamigration.model.TbArticleDO;
import com.es.datamigration.util.ConvertMapUtil;
import com.es.stone.constant.EsConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Slf4j
@Component
public class ArticleSyncToEsManager {

    private Integer page = 0;
    private Integer pageSize = 10000;

    @Autowired
    private ServiceImportManager serviceImportManager;

    @Autowired
    private TbArticleDOMapper tbArticleDOMapper;

    /**
     * 同步数据到ES主控方法
     */
    public String syncDataControl() {

        //全部查询，数据量如果过大，容易内存溢出，所以改为自动分页查询
//        List<TbArticleDO> articleDOList = tbArticleDOMapper.selectAll();

        List<TbArticleDO> articleDOResultList = new ArrayList<>();
        List<TbArticleDO> articleDOList;
        while (true) {
            articleDOList= tbArticleDOMapper.selectByPage(page, pageSize);
            articleDOResultList.addAll(articleDOList);
            page ++;
            if (articleDOList.size() < pageSize) {
                break;
            }
        }

        for (TbArticleDO tbArticleDO : articleDOResultList) {
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

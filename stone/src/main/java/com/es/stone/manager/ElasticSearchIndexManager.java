package com.es.stone.manager;

import com.es.stone.constant.EsConstant;
import com.es.stone.manager.ElasticSearchDumpManager;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.admin.indices.alias.Alias;
import org.elasticsearch.action.admin.indices.create.CreateIndexRequest;
import org.elasticsearch.common.settings.Settings;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.List;

@Slf4j
public class ElasticSearchIndexManager {

    @Autowired
    private ElasticSearchDumpManager elasticSearchDumpManager;

    /**
     * db_search.tb_article创建逻辑
     *
     * @param index
     * @return
     */
    protected CreateIndexRequest convertTbArticle(String index) {
        CreateIndexRequest createIndexRequest = new CreateIndexRequest();
        createIndexRequest.index(index + "_v1");//同步模块创建的索引，默认版本都为v1，同步时，使用别名进行同步
        createIndexRequest.alias(new Alias(index));//创建别名
        createIndexRequest.settings(Settings.builder().put(EsConstant.EsIndexProperty.NUMBER_OF_SHARDS, 7)//设置分片数
                .put(EsConstant.EsIndexProperty.MAX_RESULT_WINDOW, 100000));//设置查询条数上限
        createIndexRequest.mapping(EsConstant.EsIndexProperty.GENERAL_TYPE,
                "title", EsConstant.EsIndexWordProperty.IK,//设置 title 分词
                "content", EsConstant.EsIndexWordProperty.IK, //设置 content 分词
                "location", EsConstant.EsIndexWordProperty.GEO);//设置 location 经纬度字段，用于距离排序
        return createIndexRequest;
    }

    /**
     * 检查索引情况并根据情况创建索引
     *
     * @param index
     * @return
     */
    public boolean checkIndex(String... index) {
        boolean flag = true;
        if (index != null) {
            List<String> indexList = Arrays.asList(index);
            try {
                for (String idx : indexList) {//需特殊处理的索引列表
                    if (!elasticSearchDumpManager.isExistsIndex(idx)) {//先判断索引是否存在，若不存在，则继续判断是什么索引
                        CreateIndexRequest createIndexRequest = null;
                        if (EsConstant.EsIndexName.DB_SEARCH_TB_ARTICLE.equals(idx)) {//针对特殊索引配置初始化逻辑
                            createIndexRequest = convertTbArticle(EsConstant.EsIndexName.DB_SEARCH_TB_ARTICLE);//设置索引初始化mapper
                        }
                        //设置完成mapper后进行判断
                        if (createIndexRequest != null) {//mapper非空则创建索引
                            flag = elasticSearchDumpManager.createIndex(createIndexRequest);//根据mapper创建索引
                            if (!flag) {//若创建失败，则程序终止
                                log.error("索引" + idx + "创建失败！");
                                return flag;
                            }
                        }
                    }
                }
            } catch (Exception e) {
                flag = false;//出现异常false
                log.error("索引检查失败，程序退出！", e);
            }
        }
        return flag;
    }

}

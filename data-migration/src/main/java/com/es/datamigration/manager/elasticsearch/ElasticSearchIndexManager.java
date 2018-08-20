package com.es.datamigration.manager.elasticsearch;

import com.es.stone.manager.ElasticSearchDumpManager;
import org.elasticsearch.action.admin.indices.alias.Alias;
import org.elasticsearch.action.admin.indices.create.CreateIndexRequest;
import org.elasticsearch.common.settings.Settings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.List;

public class ElasticSearchIndexManager {

    private final static Logger logger = LoggerFactory.getLogger(ElasticSearchIndexManager.class);

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
        createIndexRequest.alias(new Alias(index));
        createIndexRequest.settings(Settings.builder().put("number_of_shards", 7).put("max_result_window", 100000));
//        createIndexRequest.mapping("doc", "title", "type=text,fielddata=true,analyzer=ik_smart",
//                "content", "type=text,fielddata=true,analyzer=ik_smart",
//                "create_time", "type=date",
//                "update_time", "type=date");
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
                        if ("db_search.tb_article".equals(idx)) {//针对特殊索引配置初始化逻辑
                            createIndexRequest = convertTbArticle("db_search.tb_article");//设置索引初始化mapper
                        }
                        //设置完成mapper后进行判断
                        if (createIndexRequest != null) {//mapper非空则创建索引
                            flag = elasticSearchDumpManager.createIndex(createIndexRequest);//根据mapper创建索引
                            if (!flag) {//若创建失败，则程序终止
                                logger.error("索引" + idx + "创建失败！");
                                return flag;
                            }
                        }
                    }
                }
            } catch (Exception e) {
                flag = false;//出现异常false
                logger.error("索引检查失败，程序退出！", e);
            }
        }
        return flag;
    }

}

package com.es.stone.manager;

import com.es.stone.constant.EsConstant;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.admin.indices.create.CreateIndexRequest;
import org.elasticsearch.action.admin.indices.create.CreateIndexResponse;
import org.elasticsearch.action.admin.indices.get.GetIndexRequest;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.Map;

@Slf4j
public class ElasticSearchDumpManager {

    @Autowired
    private ElasticSearchInitClientManager elasticSearchInitClientManager;

    /**
     * 排序组装es的id,兼容联合主键
     *
     * @param keyMap
     * @return
     */
    public String esKeyString(Map<String, String> keyMap) {
        StringBuffer sb = new StringBuffer();
        for (Map.Entry<String, String> tmpMap : keyMap.entrySet()) {
            sb.append(tmpMap.getValue());
        }
        return sb.toString();
    }

    /**
     * 同步删除ES数据方法
     *
     * @param colMap
     * @param index
     */
    public void deleteRecordToEs(Map colMap, String index) {
        String esKey = (String) colMap.get(EsConstant.ES_KEY);
        DeleteRequest request = new DeleteRequest(index, EsConstant.EsIndexProperty.GENERAL_TYPE, esKey);
        try {
            elasticSearchInitClientManager.getElasticClient().delete(request);
        } catch (Exception e) {
            log.error("ES删除数据失败：" + esKey, e);
        }
    }

    /**
     * 同步插入或更新ES数据方法
     *
     * @param colMap
     * @param index
     */
    public void insertOrUpdateToEs(Map colMap, String index) {
        insertOrUpdateToEsWithType(colMap, index, EsConstant.EsIndexProperty.GENERAL_TYPE, 0);
    }

    /**
     * 同步插入或更新ES数据方法
     *
     * @param colMap
     * @param index
     */
    public void insertOrUpdateToEsWithType(Map colMap, String index, String type, int tryCount) {
        String esKey = (String) colMap.get(EsConstant.ES_KEY);
        UpdateRequest request = new UpdateRequest(index, type, esKey);
        //移除主键值
        colMap.remove(EsConstant.ES_KEY);
        request.doc(colMap);
        request.upsert(colMap);
        RestHighLevelClient client = elasticSearchInitClientManager.getClientFromPool();
        try {
            client.update(request);
        } catch (Exception e) {
            log.error("数据同步es异常,重试次数:,{}, esKey:{}, colMap:{}", tryCount, esKey, colMap, e);
            if (tryCount < 3) {
                tryCount++;
                colMap.put(EsConstant.ES_KEY, esKey);
                insertOrUpdateToEsWithType(colMap, index, type, tryCount);
            }
        } finally {
            elasticSearchInitClientManager.disConnect(client);
        }
    }

    /**
     * 同步插入或更新ES数据方法
     *
     * @param index
     * @param type
     * @param json
     * @param key
     */
    public void insertOrUpdateToEsWithTypeUseJson(String index, String type, String json, String key) {
        IndexRequest request = new IndexRequest(index, type, key);
        request.source(json, XContentType.JSON);
        try {
            elasticSearchInitClientManager.getElasticClient().index(request);
        } catch (Exception e) {
            log.error("数据同步es异常：" + key, e);
        }
    }

    /**
     * 批量提交json到es
     *
     * @param map
     * @param index
     */
    public void insertOrUpdateToEsbatch(Map<String, String> map, String index) {
        //批量同步约战约球人员表
        BulkRequest request = new BulkRequest();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            request.add(new IndexRequest(index, index, entry.getKey()).source(entry.getValue(), XContentType.JSON));
        }
        try {
            elasticSearchInitClientManager.getElasticClient().bulk(request);
        } catch (IOException e) {
            log.error("数据同步es异常 insertOrUpdateToEs:" + index, e);
        }
    }

    /**
     * 根据配置创建索引，通用方法
     *
     * @param createRequest
     * @return
     * @throws Exception
     */
    public boolean createIndex(CreateIndexRequest createRequest) throws Exception {
        CreateIndexResponse response = elasticSearchInitClientManager.getElasticClient().indices().create(createRequest);
        return response.isAcknowledged();
    }

    /**
     * 判断索引是否存在，通用方法
     *
     * @param index
     * @return
     * @throws Exception
     */
    public boolean isExistsIndex(String... index) throws Exception {
        GetIndexRequest request = new GetIndexRequest();
        request.indices(index);
        return elasticSearchInitClientManager.getElasticClient().indices().exists(request);
    }

}

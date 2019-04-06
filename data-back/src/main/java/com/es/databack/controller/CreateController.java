package com.es.databack.controller;

import com.es.databack.enums.IndexTypeEnum;
import com.es.stone.constant.EsConstant;
import com.es.stone.manager.ElasticSearchDumpManager;
import com.es.stone.result.BaseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.admin.indices.alias.Alias;
import org.elasticsearch.action.admin.indices.create.CreateIndexRequest;
import org.elasticsearch.common.settings.Settings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 生成相关
 */
@Api("生成接口API")
@Slf4j
@RestController
@RequestMapping("/api/create/admin")
public class CreateController {

    @Autowired
    private ElasticSearchDumpManager elasticSearchDumpManager;

    /**
     * 生成索引
     *
     * @return
     */
    @ApiOperation(value = "生成索引")
    @GetMapping("/createIndex")
    public BaseResult createIndex(@RequestParam Integer numberOfShards,
                                  @RequestParam String alias,
                                  @RequestParam Integer maxResultWindow,
                                  @RequestParam String dbName,
                                  @RequestParam String tableName,
                                  @RequestParam String[] columns,
                                  @RequestParam String[] indexTypes) {
        log.info("createIndex numberOfShards:{} alias:{} maxResultWindow:{} dbName:{} tableName:{} columns:{} indexTypes:{}",
                numberOfShards, alias, maxResultWindow, dbName, tableName, columns, indexTypes);

        //检查索引是否存在
        try {
            if (elasticSearchDumpManager.isExistsIndex(dbName + "." + tableName))
                return BaseResult.buildSuccessResult("索引已存在！");
            //配置规则
            CreateIndexRequest createIndexRequest = createIndexRequest(dbName + "." + tableName,
                    alias, numberOfShards, maxResultWindow, columns, indexTypes);
            //生成索引
            if (!elasticSearchDumpManager.createIndex(createIndexRequest)) {//若创建失败，则程序终止
                log.error("索引" + dbName + tableName + "创建失败！");
                return BaseResult.buildSuccessResult(Boolean.FALSE);
            } else {
                return BaseResult.buildSuccessResult(Boolean.TRUE);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return BaseResult.buildSuccessResult(Boolean.FALSE);
        }
    }

    /**
     * 配置索引请求规则
     * @param index
     * @return
     */
    protected CreateIndexRequest createIndexRequest(String index, String alias, Integer numberOfShards,
                                                    Integer maxResultWindow, String[] columns, String[] indexTypes) {
        CreateIndexRequest createIndexRequest = new CreateIndexRequest();
        createIndexRequest.index(index + alias);
        createIndexRequest.alias(new Alias(index));//创建别名
        createIndexRequest.settings(Settings.builder().put(EsConstant.EsIndexProperty.NUMBER_OF_SHARDS, numberOfShards)//设置分片数
                .put(EsConstant.EsIndexProperty.MAX_RESULT_WINDOW, maxResultWindow));//设置查询条数上限
//        createIndexRequest.mapping(EsConstant.EsIndexProperty.GENERAL_TYPE);
        if (columns.length > 0 && indexTypes.length > 0) {
            for (int i = 0; i< columns.length; i++) {
                createIndexRequest.mapping(EsConstant.EsIndexProperty.GENERAL_TYPE, columns[i], IndexTypeEnum.getIndexStrByIndexType(indexTypes[i]));
            }
        }
        return createIndexRequest;
    }

    /**
     * 生成同步代码
     *
     * @return
     */
    @ApiOperation(value = "生成同步代码")
    @GetMapping("/createMigrationCode")
    public BaseResult createMigrationCode(@RequestParam Integer numberOfShards,
                                  @RequestParam String alias,
                                  @RequestParam Integer maxResultWindow,
                                  @RequestParam String dbName,
                                  @RequestParam String tableName,
                                  @RequestParam String[] columns,
                                  @RequestParam String[] indexTypes) {
        log.info("createIndex numberOfShards:{} alias:{} maxResultWindow:{} dbName:{} tableName:{} columns:{} indexTypes:{}",
                numberOfShards, alias, maxResultWindow, dbName, tableName, columns, indexTypes);



        return BaseResult.buildSuccessResult(Boolean.TRUE);
    }

}

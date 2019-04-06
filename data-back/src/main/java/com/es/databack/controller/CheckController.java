
package com.es.databack.controller;

import com.es.databack.enums.IndexTypeEnum;
import com.es.stone.result.BaseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * 校验相关
 */
@Api("校验API")
@Slf4j
@RestController
@RequestMapping("/api/check/admin")
public class CheckController {

    private Map<String, String> columnMap = new HashMap<>();

    @Value("${local.jdbc.url}")
    private String url;

    @Value("${local.jdbc.username}")
    private String username;

    @Value("${local.jdbc.password}")
    private String password;

    /**
     * 检查索引相关配置
     *
     * @return
     */
    @ApiOperation(value = "检查索引相关配置")
    @GetMapping("/index")
    public BaseResult checkIndex(@RequestParam Integer numberOfShards,
                                 @RequestParam String alias,
                                 @RequestParam Integer maxResultWindow) {
        log.info("checkDatabase numberOfShards:{} alias:{} maxResultWindow:{}",
                numberOfShards, alias, maxResultWindow);

        //分片数必须大于0
        //别名可以为null或者""，不做校验
        //查询最大值必须大于100
        if (Objects.isNull(numberOfShards) || numberOfShards <= 0) {
            return BaseResult.buildSuccessResult("分片数必须大于0！");
        }

        if (Objects.isNull(maxResultWindow) || maxResultWindow <= 100) {
            return BaseResult.buildSuccessResult("查询最大值必须大于100！");
        }
        return BaseResult.buildSuccessResult("success");

    }

    /**
     * 检查数据库相关配置
     *
     * @return
     */
    @ApiOperation(value = "检查数据库相关配置")
    @GetMapping("/database")
    public BaseResult checkDatabase(@RequestParam String dbName,
                                    @RequestParam String tableName,
                                    @RequestParam String[] columns,
                                    @RequestParam String[] indexTypes) {
        log.info("checkDatabase dbName:{} tableName:{} columns:{} indexTypes:{}",
                dbName, tableName, columns, indexTypes);

        //校验传参
        if (columns.length != indexTypes.length) {
            return BaseResult.buildSuccessResult("传入字段数量与索引类型数量不匹配！");
        }

        try {
            //检查数据库连接
            Connection connection = DriverManager.getConnection(url, username, password);

            if (connection == null) {
                return BaseResult.buildSuccessResult("数据库配置错误！");
            }

            //检查表
            if (!checkTable(connection, tableName)) {
                return BaseResult.buildSuccessResult("数据库不存在这个表！");
            }

            //校验字段
            if (!checkColumn(tableName, columns)) {
                return BaseResult.buildSuccessResult("表中不存在某个字段！");
            }

            //检查字段对应的索引格式
            if (!checkIndexType(columns, indexTypes)) {
                return BaseResult.buildSuccessResult("字段类型和索引类型不匹配！");
            }

            connection.close();
            return BaseResult.buildSuccessResult("success");

        } catch (Exception e) {
            log.error("", e);
            return BaseResult.buildSuccessResult("系统错误");
        }
    }

    private boolean checkTable(Connection conn, String tableName) {
        log.info("checkColumn conn:{} tableName:{}", conn, tableName);

        try {
            //避免表数据过多，造成内存泄漏，只查一条数据，可以获取字段属性就好。
            String sql = "select * from " + tableName + " limit 0,1";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery(sql);
            ResultSetMetaData data = rs.getMetaData();

            for (int i = 1; i <= data.getColumnCount(); i++) {
                // 获得所有列的数目及实际列数
                int columnCount = data.getColumnCount();
                // 获得指定列的列名
                String columnName = data.getColumnName(i);
                // 获得指定列的列值
                int columnType = data.getColumnType(i);
                // 获得指定列的数据类型名
                String columnTypeName = data.getColumnTypeName(i);
                // 所在的Catalog名字
                String catalogName = data.getCatalogName(i);
                // 对应数据类型的类
                String columnClassName = data.getColumnClassName(i);
                // 在数据库中类型的最大字符个数
                int columnDisplaySize = data.getColumnDisplaySize(i);
                // 默认的列的标题
                String columnLabel = data.getColumnLabel(i);
                // 获得列的模式
                String schemaName = data.getSchemaName(i);
                // 某列类型的精确度(类型的长度)
                int precision = data.getPrecision(i);
                // 小数点后的位数
                int scale = data.getScale(i);
                // 获取某列对应的表名
                String tableName2 = data.getTableName(i);
                // 是否自动递增
                boolean isAutoIncrement = data.isAutoIncrement(i);
                // 在数据库中是否为货币型
                boolean isCurrency = data.isCurrency(i);
                // 是否为空
                int isNullable = data.isNullable(i);
                // 是否为只读
                boolean isReadOnly = data.isReadOnly(i);
                // 能否出现在where中
                boolean isSearchable = data.isSearchable(i);
//                System.out.println(columnCount);
//                System.out.println("获得列" + i + "的字段名称:" + columnName);
//                System.out.println("获得列" + i + "的类型,返回SqlType中的编号:" + columnType);
//                System.out.println("获得列" + i + "的数据类型名:" + columnTypeName);
//                System.out.println("获得列" + i + "所在的Catalog名字:" + catalogName);
//                System.out.println("获得列" + i + "对应数据类型的类:" + columnClassName);
//                System.out.println("获得列" + i + "在数据库中类型的最大字符个数:" + columnDisplaySize);
//                System.out.println("获得列" + i + "的默认的列的标题:" + columnLabel);
//                System.out.println("获得列" + i + "的模式:" + schemaName);
//                System.out.println("获得列" + i + "类型的精确度(类型的长度):" + precision);
//                System.out.println("获得列" + i + "小数点后的位数:" + scale);
//                System.out.println("获得列" + i + "对应的表名:" + tableName2);
//                System.out.println("获得列" + i + "是否自动递增:" + isAutoIncrement);
//                System.out.println("获得列" + i + "在数据库中是否为货币型:" + isCurrency);
//                System.out.println("获得列" + i + "是否为空:" + isNullable);
//                System.out.println("获得列" + i + "是否为只读:" + isReadOnly);
//                System.out.println("获得列" + i + "能否出现在where中:" + isSearchable);

                columnMap.put(columnName, columnTypeName);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }

    private boolean checkColumn(String tableName, String[] columns) {
        log.info("checkColumn tableName:{} columns:{}", tableName, columns);

        if (columns.length == 0) {
            log.info("校验字段不能为null");
            return Boolean.FALSE;
        }
        for (String columnName : columns) {
            if (!columnMap.keySet().contains(columnName)) {
                System.out.println("Column " + columnName + " not exist in Table " + tableName);
                return Boolean.FALSE;
            }
        }
        return Boolean.TRUE;
    }

    private boolean checkIndexType(String[] columns, String[] indexTypes) {
        for (int i = 0; i < indexTypes.length; i++) {
            //检查表中字段类型，是否匹配索引类型
            if (!IndexTypeEnum.getByIndexType(indexTypes[i]).equals(columnMap.get(columns[i])))
                return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }

}

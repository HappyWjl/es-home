
package com.es.databack.controller;

import com.es.databack.enums.IndexTypeEnum;
import com.es.stone.result.BaseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.*;
import java.util.*;

/**
 * 配置相关
 */
@Api("配置API")
@Slf4j
@RestController
@RequestMapping("/api/config/admin")
public class ConstantController {

    @Value("${local.jdbc.url}")
    private String url;

    @Value("${local.jdbc.username}")
    private String username;

    @Value("${local.jdbc.password}")
    private String password;

    /**
     * 获取数据库名称
     *
     * @return
     */
    @ApiOperation(value = "获取数据库名称")
    @GetMapping("/database")
    public BaseResult database() {
        try {
            //检查数据库连接
            Connection connection = DriverManager.getConnection(url, username, password);
            DatabaseMetaData dbMetaData = connection.getMetaData();
            ResultSet rs = dbMetaData.getCatalogs();
            connection.close();
            while (rs.next()) {
                String name = rs.getString("TABLE_CAT");
                return BaseResult.buildSuccessResult(name);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return BaseResult.buildSuccessResult("");
    }

    /**
     * 获取数据库表名列表
     *
     * @return
     */
    @ApiOperation(value = "获取数据库表名列表")
    @GetMapping("/table")
    public BaseResult table() {
        List<String> list = new ArrayList<>();
        try {
            //检查数据库连接
            Connection connection = DriverManager.getConnection(url, username, password);
            DatabaseMetaData dbMetaData = connection.getMetaData();
            ResultSet rs = dbMetaData.getTables(null, null, null,new String[] { "TABLE" });
            while (rs.next()) {
                list.add(rs.getString("TABLE_NAME"));
            }
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return BaseResult.buildSuccessResult(list);
    }

    /**
     * 获取索引类型列表
     *
     * @return
     */
    @ApiOperation(value = "获取索引类型列表")
    @GetMapping("/index")
    public BaseResult index() {
        return BaseResult.buildSuccessResult(IndexTypeEnum.getIndexTypeList());
    }

}

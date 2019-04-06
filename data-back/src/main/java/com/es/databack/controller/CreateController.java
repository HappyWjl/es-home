package com.es.databack.controller;

import com.es.databack.common.Column;
import com.es.databack.common.Table;
import com.es.databack.enums.IndexTypeEnum;
import com.es.databack.enums.SqlTypeEnum;
import com.es.databack.util.CamelCaseUtils;
import com.es.databack.util.FileHelper;
import com.es.stone.constant.EsConstant;
import com.es.stone.manager.ElasticSearchDumpManager;
import com.es.stone.result.BaseResult;
import com.mysql.jdbc.StringUtils;
import freemarker.template.Configuration;
import freemarker.template.Template;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.admin.indices.alias.Alias;
import org.elasticsearch.action.admin.indices.create.CreateIndexRequest;
import org.elasticsearch.common.settings.Settings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.swing.filechooser.FileSystemView;
import java.io.*;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 生成相关
 */
@Api("生成接口API")
@Slf4j
@RestController
@RequestMapping("/api/create/admin")
public class CreateController {

    @Value("${local.jdbc.url}")
    private String url;

    @Value("${local.jdbc.username}")
    private String username;

    @Value("${local.jdbc.password}")
    private String password;

    @Value("${tableRemovePrefixes}")
    private String tableRemovePrefixes;

    @Value("${outRoot}")
    private String outRoot;

    @Value("${basePackage}")
    private String basePackage;

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
                                  @RequestParam(required=false) String[] columns,
                                  @RequestParam(required=false) String[] indexTypes) {
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
    public BaseResult createMigrationCode(@RequestParam String tableName,
                                          @RequestParam String tableRemark) {
        log.info("createIndex tableName:{} tableRemark:{}", tableName, tableRemark);

        //获取配置信息
        //解析数据表
        try {
            gen(tableName, tableRemark, "id", "Id");
        } catch (Exception e) {
            log.error("createMigrationCode is error!", e);
            return BaseResult.buildSuccessResult(Boolean.FALSE);
        }
        return BaseResult.buildSuccessResult(Boolean.TRUE);
    }

    /**
     * 解析数据表
     *
     * @param tableName
     * @return
     * @throws Exception
     */
    public Table parseTable(String tableName) throws Exception {

        Connection conn = java.sql.DriverManager.getConnection(url, username, password);
        DatabaseMetaData dmd = conn.getMetaData();

        ResultSet rs = dmd.getColumns(null, null, tableName, "%");
        List<Column> columns = new ArrayList<Column>();
        while (rs.next()) {
            Column c = new Column();

            c.setLabel(rs.getString("REMARKS"));

            String name = rs.getString("COLUMN_NAME");
            c.setName(CamelCaseUtils.toCamelCase(name));
            c.setDbName(name);

            String dbType = rs.getString("TYPE_NAME");

            int columnSize = rs.getInt("COLUMN_SIZE");
            if (dbType.equals("TINYINT") && columnSize > 1) {
                c.setType("Integer");
            } else if (dbType.equals("TINYINT") && columnSize == 1) {
                c.setType("Boolean");
            } else {
                String type = SqlTypeEnum.getJavaTypeBySqlType(dbType);
                c.setType(type == null ? "String" : type);
            }
            c.setDbType(dbType);

            c.setLength(rs.getInt("COLUMN_SIZE"));
            c.setDecimalDigits(rs.getInt("DECIMAL_DIGITS"));
            c.setNullable(rs.getBoolean("NULLABLE"));
            columns.add(c);
        }

        List<Column> pkColumns = new ArrayList<Column>();
        ResultSet pkrs = dmd.getPrimaryKeys(null, null, tableName);
        while (pkrs.next()) {
            Column c = new Column();
            String name = pkrs.getString("COLUMN_NAME");
            c.setName(CamelCaseUtils.toCamelCase(name));
            c.setDbName(name);
            pkColumns.add(c);
        }

        conn.close();

        Table t = new Table();
        String name = tableName;
        if (tableRemovePrefixes != null && !"".equals(tableRemovePrefixes)) {
            name = tableName.split(tableRemovePrefixes)[1];
        }
        t.setName(CamelCaseUtils.toCamelCase(name));
        t.setDbName(tableName);
        t.setColumns(columns);
        t.setPkColumns(pkColumns);
        return t;
    }

    /**
     * <p>Discription:[生成映射文件和实体类]</p>
     * Created on 2019年4月4日
     *
     * @param tableName       要声称映射文件和实体类的表名称
     * @param tableDescAndCat 表描述
     * @throws Exception
     */
    public void gen(String tableName, String tableDescAndCat, String id, String modelId) throws Exception {
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_21);

        //当输出地址为null时，文件放到桌面
        if (StringUtils.isNullOrEmpty(outRoot)) {
            File desktopDir = FileSystemView.getFileSystemView() .getHomeDirectory();
            outRoot = desktopDir.getAbsolutePath() + "/Desktop/EasyCodeDemo";
        }

        //获取当前日期
        SimpleDateFormat sm_date = new SimpleDateFormat("yyyy年MM月dd日");
        SimpleDateFormat sm_year = new SimpleDateFormat("yyyy年");

        //将首字母转为大写
        StringBuffer buffer = new StringBuffer();
        String namePart1 = modelId.substring(0, 1).toUpperCase();
        String namePart2 = modelId.substring(1);
        buffer.append(namePart1 + namePart2);

        System.out.println(buffer);

        Map<String, Object> root = new HashMap<>();
        Table t = this.parseTable(tableName);
        t.setTableDesc(tableDescAndCat.split("_")[0]);
        root.put("table", t);
        root.put("className", t.getNameUpper());
        root.put("classNameLower", t.getName());
        root.put("primaryKey", id);
        root.put("modelId", modelId);
        root.put("modelIdFirstUpper", buffer);
        root.put("package", basePackage);
        root.put("date", sm_date.format(new Date()));
        root.put("year", sm_year.format(new Date()));

        root.put("author", "Happy王子乐");
        root.put("email", "820155406@qq.com");
        root.put("website", "得码网");

        String templateDir = this.getClass().getClassLoader().getResource("templates/migration").getPath();

        File tdf = new File(templateDir);
        List<File> files = FileHelper.findAllFile(tdf);

        for (File f : files) {
            String parentDir = "";
            if (f.getParentFile().compareTo(tdf) != 0) {
                parentDir = f.getParent().split("templates")[1];
            }
            cfg.setClassForTemplateLoading(this.getClass(), "/templates" + parentDir);

            Template template = cfg.getTemplate(f.getName());
            template.setEncoding("UTF-8");
            String parentFileDir = FileHelper.genFileDir(parentDir, root);
            parentFileDir = parentFileDir.replace(".", "/");
            String file = FileHelper.genFileDir(f.getName(), root).replace(".ftl", ".java");

            File newFile = FileHelper.makeFile(outRoot + parentFileDir + "/" + file);
            Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(newFile), "UTF-8"));
            template.process(root, out);
            log.debug("已生成文件：" + outRoot + parentFileDir + "/" + file);
            System.out.println("已生成文件：" + outRoot + parentFileDir + "/" + file);
        }
    }

}

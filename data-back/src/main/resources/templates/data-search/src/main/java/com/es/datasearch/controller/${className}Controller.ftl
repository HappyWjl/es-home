package ${package}.controller;

import ${package}.param.Query${className}SearchVO;
import ${package}.result.ResultByEsDO;
import ${package}.service.${className}Service;
import lombok.extern.slf4j.Slf4j;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/** 
 * <p>Description: [${table.tableDesc}搜索]</p>
 * Created on ${date}
 * @author  <a href="mailto: ${email}">${author}</a>
 * @version 1.0 
 * Copyright (c) ${year} ${website}
 */
@Api("${className} 搜索 API")
@Slf4j
@RestController
@RequestMapping("/api/${classNameLower}")
public class ${className}Controller {

    @Autowired
    private ${className}Service ${classNameLower}Service;

    /**
    * 根据条件查询列表
    * @return
    */
    @PostMapping("/get${className}List")
    public ResultByEsDO get${className}List(@RequestBody Query${className}SearchVO query${className}SearchVO) {
        return ${classNameLower}Service.get${className}List(query${className}SearchVO);
    }

}

package ${package}.controller;

import ${package}.manager.${classNameLower}.${className}ToEsManager;
import lombok.extern.slf4j.Slf4j;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/** 
 * <p>Description: [${table.tableDesc}历史数据同步]</p>
 * Created on ${date}
 * @author  <a href="mailto: ${email}">${author}</a>
 * @version 1.0 
 * Copyright (c) ${year} ${website}
 */
@Api("${className} 历史数据同步 API")
@Slf4j
@RestController
@RequestMapping("/api/${classNameLower}")
public class ${className}Controller {

    @Autowired
    private ${className}ToEsManager ${classNameLower}ToEsManager;

    /**
    * 开启同步数据，将表中数据同步到ES
    * @return
    */
    @GetMapping("/${classNameLower}ToEs")
    public String syncPlaceToEs() {
        return ${classNameLower}ToEsManager.syncDataControl();
    }

}

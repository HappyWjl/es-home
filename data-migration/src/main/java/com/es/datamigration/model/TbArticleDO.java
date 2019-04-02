package com.es.datamigration.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class TbArticleDO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String title;

    private String content;

    private Integer state;

    private Double latitude;

    private Double longitude;

    private Date createTime;

    private Date updateTime;

}

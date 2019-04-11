package com.es.datasearch.result;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

@Data
@Builder
public class ResultByEsDO implements Serializable {

    private int pageNo;

    private int pageSize;

    private List<Map> list;

    private String esStatus;

    private int recordSize;

}

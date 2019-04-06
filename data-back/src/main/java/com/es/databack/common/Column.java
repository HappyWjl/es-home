package com.es.databack.common;

import lombok.Data;

@Data
public class Column {

    private String name;
    private String dbName;
    private String label;
    private String type;
    private String dbType;
    private Integer length;
    private Boolean nullable;
    private Integer decimalDigits;

    public String getNameUpper() {
        return name.replaceFirst(name.substring(0, 1), name.substring(0, 1).toUpperCase());
    }

}

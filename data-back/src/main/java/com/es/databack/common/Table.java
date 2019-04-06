package com.es.databack.common;

import lombok.Data;

import java.util.List;

@Data
public class Table {

    private String name;
    private String dbName;
    private String tableDesc;
    private List<Column> columns;
    private List<Column> pkColumns;

    public String getNameUpper() {
        return name.replaceFirst(name.substring(0, 1), name.substring(0, 1).toUpperCase());
    }

}

package com.es.databack.enums;

import java.util.ArrayList;
import java.util.List;

public enum IndexTypeEnum {

    GEO("geo","DOUBLE"),
    IK_SMART("ik_smart","VARCHAR");

    private final String indexType;
    private final String sqlType;

    IndexTypeEnum(String indexType, String sqlType) {
        this.indexType = indexType;
        this.sqlType = sqlType;
    }

    public static String getByIndexType(String indexType) {
        for(IndexTypeEnum indexTypeEnum : values()) {
            if(indexTypeEnum.indexType.equals(indexType)) {
                return indexTypeEnum.sqlType;
            }
        }
        return "";
    }

    public static List<String> getIndexTypeList() {
        List<String> list = new ArrayList<>();
        for(IndexTypeEnum indexTypeEnum : values()) {
            list.add(indexTypeEnum.indexType);
        }
        return list;
    }

}

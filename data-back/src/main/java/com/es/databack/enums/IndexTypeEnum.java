package com.es.databack.enums;

import java.util.ArrayList;
import java.util.List;

public enum IndexTypeEnum {

    GEO("geo","DOUBLE", "type=geo_point"),
    IK_SMART("ik_smart","VARCHAR", "type=text,fielddata=true,analyzer=ik_smart");

    private final String indexType;
    private final String sqlType;
    private final String indexStr;

    IndexTypeEnum(String indexType, String sqlType, String indexStr) {
        this.indexType = indexType;
        this.sqlType = sqlType;
        this.indexStr = indexStr;
    }

    public static String getSqlTypeByIndexType(String indexType) {
        for(IndexTypeEnum indexTypeEnum : values()) {
            if(indexTypeEnum.indexType.equals(indexType)) {
                return indexTypeEnum.sqlType;
            }
        }
        return "";
    }

    public static String getIndexStrByIndexType(String indexType) {
        for(IndexTypeEnum indexTypeEnum : values()) {
            if(indexTypeEnum.indexType.equals(indexType)) {
                return indexTypeEnum.indexStr;
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

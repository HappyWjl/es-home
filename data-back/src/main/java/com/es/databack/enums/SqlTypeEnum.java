package com.es.databack.enums;

public enum SqlTypeEnum {

    CHAR("CHAR","String"),
    VARCHAR("VARCHAR","String"),
    LONGVARCHAR("LONGVARCHAR","String"),
    TINYINT("TINYINT","Integer"),
    TINYINT_UNSIGNED("TINYINT UNSIGNED","Integer"),
    NUMERIC("NUMERIC","Double"),
    DECIMAL("DECIMAL","java.math.BigDecimal"),
    BIGINT("BIGINT","Long"),
    BIGINT_UNSIGNED("BIGINT UNSIGNED","Long"),
    REAL("REAL","Double"),
    SMALLINT("SMALLINT","Integer"),
    INTEGER("INTEGER","Integer"),
    INTEGER_UNSIGNED("INTEGER UNSIGNED","Integer"),
    INT("INT","Integer"),
    INT_UNSIGNED("INT UNSIGNED","Integer"),
    DATE("DATE","java.util.Date"),
    TIME("TIME","java.util.Date"),
    DATETIME("DATETIME","java.util.Date"),
    TIMESTAMP("TIMESTAMP","java.util.Date"),
    BIT("BITD","Boolean");

    private final String sqlType;
    private final String javaType;

    SqlTypeEnum(String sqlType, String javaType) {
        this.sqlType = sqlType;
        this.javaType = javaType;
    }

    public static String getJavaTypeBySqlType(String sqlType) {
        for(SqlTypeEnum sqlTypeEnum : values()) {
            if(sqlTypeEnum.sqlType.equals(sqlType)) {
                return sqlTypeEnum.javaType;
            }
        }
        return "";
    }

}

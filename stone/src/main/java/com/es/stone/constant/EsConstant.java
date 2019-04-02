package com.es.stone.constant;

public class EsConstant {

    public final static String ES_KEY = "es_key";

    public final static String ID = "id";

    /**
     * es索引属性
     */
    public class EsIndexProperty {

        public final static String GENERAL_TYPE = "doc";

        public final static String NUMBER_OF_SHARDS = "number_of_shards";

        public final static String MAX_RESULT_WINDOW = "max_result_window";

    }

    /**
     * es索引字段属性
     */
    public class EsIndexWordProperty {

        public final static String IK = "type=text,fielddata=true,analyzer=ik_smart";

        public final static String GEO = "type=geo_point";

    }

    /**
     * es索引名称
     */
    public class EsIndexName {

        public final static String DB_SEARCH_TB_ARTICLE = "db_search.tb_article";

        public final static String DB_SEARCH_TB_TS = "db_search.tb_ts";

    }

    /**
     * 数据库特殊字段
     */
    public class SqlWord {

        public final static String TIMESTAMP = "timestamp";

        public final static String DATETIME = "datetime";

        public final static String DATE = "date";

        public final static String TIME = "time";

        public final static String BLOB = "blob";

        public final static String CREATE_TIME = "create_time";

        public final static String UPDATE_TIME = "update_time";

    }

    /**
     * geo搜索，相关
     */
    public class GEO {

        public final static String LONGITUDE = "longitude";

        public final static String LATITUDE = "latitude";

        public final static String LOCATION = "location";

    }

}

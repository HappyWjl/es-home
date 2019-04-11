package com.es.datasearch;

public class CreateSqlTest {

    public static void main(String[] args) {
        String s = "('%s', '我是测试数据标题%s', '我是测试数据内容%s', '2', '22.539051', '113.952254', '2018-08-20 11:03:00', '2018-08-20 00:00:00'),";

        for(int i = 31; i<1000; i++) {
            System.out.println(String.format(s, i, i, i));
        }
    }

}

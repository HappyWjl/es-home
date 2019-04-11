package com.es.databack.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseUtil {

    /**
     * 获取数据库链接
     * @param driver
     * @param url
     * @param name
     * @param pwd
     * @return
     */
    public static Connection getConnection(String driver, String url, String name, String pwd){
        try{
            Class.forName(driver);
            Connection conn= DriverManager.getConnection(url,name,pwd);//获取连接对象
            return conn;
        }catch(ClassNotFoundException e){
            e.printStackTrace();
            return null;
        }catch(SQLException e){
            e.printStackTrace();
            return null;
        }
    }

}

package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DBContext {
    protected Connection connection;

    public DBContext() {
        try {
            String url = "jdbc:sqlserver://MerchShopDB.mssql.somee.com:1433;databaseName=MerchShopDB;encrypt=true;trustServerCertificate=true;";
String user = "AnhKheng_SQLLogin_1";
String pass = "o7lgqrr2s7";            
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            connection = DriverManager.getConnection(url, user, pass);
            
        } catch (ClassNotFoundException | SQLException ex) {
            throw new RuntimeException("Không thể kết nối đến cơ sở dữ liệu: " + ex.getMessage());
        }
    }
}
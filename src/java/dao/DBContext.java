package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DBContext {
    protected Connection connection;

    public DBContext() {
        try {
            String user = "sa";
            String pass = "123"; 
            
            String url = "jdbc:sqlserver://localhost:1433;databaseName=MerchShopDB;encrypt=true;trustServerCertificate=true;";
            
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            connection = DriverManager.getConnection(url, user, pass);
            
        } catch (ClassNotFoundException | SQLException ex) {
            throw new RuntimeException("Không thể kết nối đến cơ sở dữ liệu: " + ex.getMessage());
        }
    }
}
package login_register;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    private static final String DB_NAME = "myprotein";
    private static final String JDBC_URL = "jdbc:mysql://localhost:3307/"+DB_NAME;
    private static final String JDBC_USER = "root";
    private static final String JDBC_PASSWORD = "";
    static Connection con;

    public static Connection getConnection() { // 连接数据库
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");//注册驱动
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            con = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return con;
    }


}

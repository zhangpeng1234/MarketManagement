package duan.db;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;
/**
 * @author zhang
 * @version 1.0
 * 连接mysql数据库
 */
public final class DbConn {
    public static  Connection getconn()
    {
        Connection conn = null;

        String user   = "root";
        String passwd = "root";
        String url = "jdbc:mysql://localhost:3306/shop";

        //已加载完驱动
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url,user,passwd);
        }catch (SQLException e)
        {
            e.printStackTrace();
        }
        catch (ClassNotFoundException e)
        {
            e.printStackTrace();
        }
        return conn;
    }

}


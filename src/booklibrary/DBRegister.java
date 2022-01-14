package booklibrary;

import java.sql.*;

public class DBRegister {

    public static Connection getConnection() {

        Connection con = null;
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            String DB_NAME = "jdbc:oracle:thin:@saloooooooh:1521/XE";
            String USER = "book";
            String PASSWORD = "book";
            con = DriverManager.getConnection(DB_NAME, USER,PASSWORD );
            String ENCODING = "?useUnicode=yes&characterEncoding=UTF-8";
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        return con;
    }

}

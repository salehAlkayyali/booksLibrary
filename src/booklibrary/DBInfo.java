
package booklibrary;
import java.sql.*;

public interface DBInfo {
 
    String DB_NAME = "jdbc:oracle:thin:@saloooooooh:1521/XE";
 
    String ENCODING = "?useUnicode=yes&characterEncoding=UTF-8";
 
    String DB_NAME_WITH_ENCODING = DB_NAME + ENCODING;
 
    String USER = "book";
 
    String PASSWORD = "book";
    
}

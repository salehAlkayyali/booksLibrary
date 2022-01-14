
package booklibrary;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class Common implements DBInfo {
    
    public static Connection getConnection()
    {
        Connection con;

        try {
             con = DriverManager.getConnection(
                    "jdbc:oracle:thin:@//saloooooooh:1521/XE", "book", "book");
                 String ENCODING = "?useUnicode=yes&characterEncoding=UTF-8";

//            con = DriverManager.getConnection(DB_NAME_WITH_ENCODING, USER, PASSWORD);
            return con;
        }
        catch (SQLException ex) {
            return null;
        }
    }
    
    
    public static String saveSelectedImage(File selectedFile)
    {
       
        String createImagePath = "";
        try {
            FileInputStream in = new FileInputStream(selectedFile);

            FileOutputStream out = new FileOutputStream(createImagePath);
            
            int c;
            while ((c = in.read()) != -1) {
                out.write(c);
            }

            in.close();
            out.close();
        }
        catch(Exception e) {}
        
        return createImagePath;
    }
    
    public static void deleteImage(String filePath)
    {
        try {
            File imageToDelete = new File(filePath);
            imageToDelete.delete();
        }
        catch(Exception e) {}
    }
    
}


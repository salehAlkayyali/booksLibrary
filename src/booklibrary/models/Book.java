package booklibrary.models;

import booklibrary.Common;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author Saleh Alkayyali
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Book {

    private int id;
    private String name;
    private int createdBy;
    private Date createdAt;
    private int totalCopies;
    private double price;
    private String description;
//    private String image;
    private User owner;
    private boolean isForSale;
    private int availableCopies;

    public void save() {
        try {
            // سيتم الإتصال مع قاعدة البيانات
            Connection con = Common.getConnection();
            // إذا نجح الإتصال, سيتم تجهيز الإستعلام الذي سيتم تنفيذه في قاعدة البيانات لحفظ المعلومات المدخلة في الحقول
            PreparedStatement ps;

            // إذا لم يقم المستخدم بوضع صورة للمنتج, سيتم تخزين فقط المعلومات التي أدخلها في الحقول
            // إذا قام المستخدم بوضع صورة للمنتج, سيتم تخزين المعلومات التي أدخلها في الحقول مع الصورة التي إختارها أيضاً
            ps = con.prepareStatement("INSERT INTO BOOKS(ID,NAME,CREATED_BY,CREATED_AT,TOTAL_COPIES, PRICE,DESCRITPION) values(?,?,?,?,?,?,?);");
            ps.toString();

            ps.setInt(1, getId());
            ps.setString(2, getName());
            ps.setInt(3, getCreatedBy());
            LocalDate todayLocalDate = LocalDate.now();
            Date sqlDate = Date.valueOf(todayLocalDate);
            ps.setDate(4, sqlDate);
            ps.setInt(5, getTotalCopies());
            ps.setDouble(6, getPrice());
            ps.setString(7, getDescription());
//            ps.setString(8, name);

//                // تاريخ إضافة المنتج سيتم تخزينه بشكل تلقائي
//                ps.setDate(3, sqlDate);
            // في الأخير سيتم تنفيذ الإستعلام و إغلاق الإتصال مع قاعدة البيانات
            ps.executeUpdate();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void create() {
        try {
            // سيتم الإتصال مع قاعدة البيانات
            Connection con = Common.getConnection();
            // إذا نجح الإتصال, سيتم تجهيز الإستعلام الذي سيتم تنفيذه في قاعدة البيانات لحفظ المعلومات المدخلة في الحقول
            PreparedStatement ps;

            // إذا لم يقم المستخدم بوضع صورة للمنتج, سيتم تخزين فقط المعلومات التي أدخلها في الحقول
            // إذا قام المستخدم بوضع صورة للمنتج, سيتم تخزين المعلومات التي أدخلها في الحقول مع الصورة التي إختارها أيضاً
            ps = con.prepareStatement("INSERT INTO BOOKS(ID,NAME,CREATED_BY,CREATED_AT,TOTAL_COPIES, DESCRITPION,USERID) values(?,?,?,?,?,?,?,?)");
            ps.toString();

            ps.setInt(1, getId());
            ps.setString(2, getName());
            ps.setInt(3, getCreatedBy());
            LocalDate todayLocalDate = LocalDate.now();
            Date sqlDate = Date.valueOf(todayLocalDate);
            ps.setDate(4, sqlDate);
            ps.setInt(5, getTotalCopies());
            ps.setDouble(6, getPrice());
            ps.setString(7, getDescription());
//            ps.setString(8, name);

            // في الأخير سيتم تنفيذ الإستعلام و إغلاق الإتصال مع قاعدة البيانات
            ps.executeUpdate();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update() {
        try {
            // إذا نجح الإتصال, سيتم تجهيز الإستعلام الذي سيتم تنفيذه في قاعدة البيانات لحفظ المعلومات المدخلة في الحقول
            try ( // سيتم الإتصال مع قاعدة البيانات
                    Connection con = Common.getConnection()) {
                // إذا نجح الإتصال, سيتم تجهيز الإستعلام الذي سيتم تنفيذه في قاعدة البيانات لحفظ المعلومات المدخلة في الحقول
                PreparedStatement ps;

                // إذا لم يقم المستخدم بوضع صورة للمنتج, سيتم تخزين فقط المعلومات التي أدخلها في الحقول
                // إذا قام المستخدم بوضع صورة للمنتج, سيتم تخزين المعلومات التي أدخلها في الحقول مع الصورة التي إختارها أيضاً
                ps = con.prepareStatement("UPDATE BOOKS SET NAME = ?,CREATED_BY= ?,TOTAL_COPIES= ?, PRICE= ?,DESCRITPION= ?,USERID= ? WHERE ID = ?;");
                ps.toString();

                ps.setInt(7, getId());
                ps.setString(1, getName());
                ps.setInt(2, getCreatedBy());
                ps.setInt(3, getTotalCopies());
                ps.setDouble(4, getPrice());
                ps.setString(5, getDescription());
//            ps.setString(8, name);
                ps.setInt(6, owner.getId());

//                // تاريخ إضافة المنتج سيتم تخزينه بشكل تلقائي
//                ps.setDate(3, sqlDate);
// في الأخير سيتم تنفيذ الإستعلام و إغلاق الإتصال مع قاعدة البيانات
                ps.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Book find(int id) {
        try {
            // إذا نجح الإتصال, سيتم تجهيز الإستعلام الذي سيتم تنفيذه في قاعدة البيانات لحفظ المعلومات المدخلة في الحقول
            try ( // سيتم الإتصال مع قاعدة البيانات
                    Connection con = Common.getConnection()) {
                // إذا نجح الإتصال, سيتم تجهيز الإستعلام الذي سيتم تنفيذه في قاعدة البيانات لحفظ المعلومات المدخلة في الحقول
                PreparedStatement ps;

                // إذا لم يقم المستخدم بوضع صورة للمنتج, سيتم تخزين فقط المعلومات التي أدخلها في الحقول
                // إذا قام المستخدم بوضع صورة للمنتج, سيتم تخزين المعلومات التي أدخلها في الحقول مع الصورة التي إختارها أيضاً
                ps = con.prepareStatement("SELECT * FROM BOOKS WHERE ID = ?;");
                ps.toString();

                ps.setInt(1, id);

//                // تاريخ إضافة المنتج سيتم تخزينه بشكل تلقائي
//                ps.setDate(3, sqlDate);
// في الأخير سيتم تنفيذ الإستعلام و إغلاق الإتصال مع قاعدة البيانات
                ResultSet rs = ps.executeQuery();
                int bookId = rs.getInt("ID");
                int Author = rs.getInt("CREATED_BY");
                String Discribe = rs.getString("DESCRITPION");
//                String image = rs.getString("image");
                String name = rs.getString("NAME");
                int copies = rs.getInt("TOTAL_COPIES");
                double Price = rs.getInt("PRICE");
                int userId = rs.getInt("USERID");
                Date addDate = rs.getDate("CREATED_AT");
                return 
                Book.builder().id(bookId).name(name).createdBy(Author).createdAt(addDate).totalCopies(copies).price(Price).description(Discribe));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void delete() {
        try {
            // إذا نجح الإتصال, سيتم تجهيز الإستعلام الذي سيتم تنفيذه في قاعدة البيانات لحفظ المعلومات المدخلة في الحقول
            try ( // سيتم الإتصال مع قاعدة البيانات
                    Connection con = Common.getConnection()) {
                // إذا نجح الإتصال, سيتم تجهيز الإستعلام الذي سيتم تنفيذه في قاعدة البيانات لحفظ المعلومات المدخلة في الحقول
                PreparedStatement ps;

                // إذا لم يقم المستخدم بوضع صورة للمنتج, سيتم تخزين فقط المعلومات التي أدخلها في الحقول
                // إذا قام المستخدم بوضع صورة للمنتج, سيتم تخزين المعلومات التي أدخلها في الحقول مع الصورة التي إختارها أيضاً
                ps = con.prepareStatement("DELETE FROM BOOKS WHERE ID = ?;");

                ps.setInt(1, getId());
//                
// في الأخير سيتم تنفيذ الإستعلام و إغلاق الإتصال مع قاعدة البيانات
                ps.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static List<Book> findAll() {
        try {
            // سيتم الإتصال مع قاعدة البيانات
            Connection con = Common.getConnection();

            // إذا نجح الإتصال, سيتم تجهيز الإستعلام الذي سيتم تنفيذه في قاعدة البيانات لحفظ المعلومات المدخلة في الحقول
            PreparedStatement ps;

            // إذا لم يقم المستخدم بوضع صورة للمنتج, سيتم تخزين فقط المعلومات التي أدخلها في الحقول
            ps = con.prepareStatement("SELECT * FROM BOOKS");
            ResultSet rs = ps.executeQuery();
            List<Book> books = new ArrayList();
            while (rs.next()) {
                books.add(Book.builder().id(rs.getInt("id")).name(rs.getString("name")).createdBy(rs.getInt("createdBy")).
                        
               createdAt(rs.getDate("addDate")).totalCopies(rs.getInt("bookNum")).price(rs.getDouble("price")).description(rs.getString("discribe")).rs.getInt("ownerID"));
            }
            return books;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

}
 
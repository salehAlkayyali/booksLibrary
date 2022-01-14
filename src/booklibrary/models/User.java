package booklibrary.models;

import booklibrary.BookLibrary;
import booklibrary.Common;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Saleh Alkayyali
 */
public class User {

    private int id;
    private String name;
    private String email;
    private String password;
    private String image;
    private String phoneNumber;
    private double money = 100;

    public User(int id, String name, String email, String password, String image, String phoneNumber) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.image = image;
        this.phoneNumber = phoneNumber;
    }

    public void addpurchase(double money) {
        this.money += money;
    }

    public double getMoney() {
        return money;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getId() {
        return id;
    }

    protected void setId(int id) {
        this.id = id;
    }

    public static User find(int userId) {
        try {
            // إذا نجح الإتصال, سيتم تجهيز الإستعلام الذي سيتم تنفيذه في قاعدة البيانات لحفظ المعلومات المدخلة في الحقول
            try ( // سيتم الإتصال مع قاعدة البيانات
                    Connection con = Common.getConnection()) {
                // إذا نجح الإتصال, سيتم تجهيز الإستعلام الذي سيتم تنفيذه في قاعدة البيانات لحفظ المعلومات المدخلة في الحقول
                PreparedStatement ps;

                // إذا لم يقم المستخدم بوضع صورة للمنتج, سيتم تخزين فقط المعلومات التي أدخلها في الحقول
                // إذا قام المستخدم بوضع صورة للمنتج, سيتم تخزين المعلومات التي أدخلها في الحقول مع الصورة التي إختارها أيضاً
                ps = con.prepareStatement("SELECT * FROM USERS WHERE USERID = ?");
                ps.toString();

                ps.setInt(1, userId);

//                
// في الأخير سيتم تنفيذ الإستعلام و إغلاق الإتصال مع قاعدة البيانات
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    int id = rs.getInt("USERID");
                    String name = rs.getString("NAME");
                    String email = rs.getString("EMAIL");
                    String password = rs.getString("PASSWORD");
                    String phoneNumber = rs.getString("PHONENUMBER");
                    String image = rs.getString("IMAGE");

                    return new User(id, name, email, password, image, phoneNumber);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static User findByEmail(String userEmail) {
        try {
            // إذا نجح الإتصال, سيتم تجهيز الإستعلام الذي سيتم تنفيذه في قاعدة البيانات لحفظ المعلومات المدخلة في الحقول
            try ( // سيتم الإتصال مع قاعدة البيانات
                    Connection con = Common.getConnection()) {
                // إذا نجح الإتصال, سيتم تجهيز الإستعلام الذي سيتم تنفيذه في قاعدة البيانات لحفظ المعلومات المدخلة في الحقول
                PreparedStatement ps;

                ps = con.prepareStatement("SELECT * FROM USERS WHERE EMAIL = ?");
                ps.setString(1, userEmail);

//                
// في الأخير سيتم تنفيذ الإستعلام و إغلاق الإتصال مع قاعدة البيانات
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    int id = rs.getInt("USERID");
                    String name = rs.getString("NAME");
                    String email = rs.getString("EMAIL");
                    String password = rs.getString("PASSWORD");
                    String phoneNumber = rs.getString("PHONENUMBER");
                    String image = rs.getString("IMAGE");

                    return new User(id, name, email, password, image, phoneNumber);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static List<User> findAll() {
        try {
            // سيتم الإتصال مع قاعدة البيانات
            Connection con = Common.getConnection();

            // إذا نجح الإتصال, سيتم تجهيز الإستعلام الذي سيتم تنفيذه في قاعدة البيانات لحفظ المعلومات المدخلة في الحقول
            PreparedStatement ps;

            // إذا لم يقم المستخدم بوضع صورة للمنتج, سيتم تخزين فقط المعلومات التي أدخلها في الحقول
            ps = con.prepareStatement("SELECT * FROM USERS");
            ResultSet rs = ps.executeQuery();
            List<User> users = new ArrayList();
            while (rs.next()) {
                users.add(new User(rs.getInt("USERID"), rs.getString("NAME"), rs.getString("EMAIL"), rs.getString("PASSWORD"), rs.getString("IMAGE"), rs.getString("PHONENUMBER")));
            }
            return users;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public void save() {
        try {
            // سيتم الإتصال مع قاعدة البيانات
            Connection con = Common.getConnection();
            // إذا نجح الإتصال, سيتم تجهيز الإستعلام الذي سيتم تنفيذه في قاعدة البيانات لحفظ المعلومات المدخلة في الحقول
            PreparedStatement ps;

            ps = con.prepareStatement("INSERT INTO USERS (USERID,NAME,EMAIL,PASSWORD,IMAGE, PHONENUMBER) values(?,?,?,?,?,?);");
            ps.toString();

            ps.setInt(1, id);
            ps.setString(2, name);
            ps.setString(3, email);
            ps.setString(4, password);
            ps.setString(5, image);
            ps.setString(6, phoneNumber);

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

            ps = con.prepareStatement("INSERT INTO USERS (NAME, EMAIL, PASSWORD, IMAGE, PHONENUMBER) values(?,?,?,?,?);");
            ps.toString();

            ps.setString(1, name);
            ps.setString(2, email);
            ps.setString(3, password);
            ps.setString(4, image);
            ps.setString(5, phoneNumber);

            // في الأخير سيتم تنفيذ الإستعلام و إغلاق الإتصال مع قاعدة البيانات
            ps.executeUpdate();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update() {
        try {
            // سيتم الإتصال مع قاعدة البيانات
            Connection con = Common.getConnection();
            // إذا نجح الإتصال, سيتم تجهيز الإستعلام الذي سيتم تنفيذه في قاعدة البيانات لحفظ المعلومات المدخلة في الحقول
            PreparedStatement ps;

            ps = con.prepareStatement("UPDATE USERS SET NAME=?,EMAIL=?,PASSWORD=?,IMAGE=?, PHONENUMBER=? WHERE USERID=?");
            ps.toString();

            ps.setString(1, name);
            ps.setString(2, email);
            ps.setString(3, password);
            ps.setString(4, image);
            ps.setString(5, phoneNumber);
            ps.setInt(6, id);

            // في الأخير سيتم تنفيذ الإستعلام و إغلاق الإتصال مع قاعدة البيانات
            ps.executeUpdate();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete() {
        try {
            // سيتم الإتصال مع قاعدة البيانات
            Connection con = Common.getConnection();
            // إذا نجح الإتصال, سيتم تجهيز الإستعلام الذي سيتم تنفيذه في قاعدة البيانات لحفظ المعلومات المدخلة في الحقول
            PreparedStatement ps;

            ps = con.prepareStatement("DELETE FROM USERS WHERE USERID=?");
            ps.toString();
            ps.setInt(1, id);

            // في الأخير سيتم تنفيذ الإستعلام و إغلاق الإتصال مع قاعدة البيانات
            ps.executeUpdate();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Book> findAllBooks() {
        try {
            // سيتم الإتصال مع قاعدة البيانات
            Connection con = Common.getConnection();

            // إذا نجح الإتصال, سيتم تجهيز الإستعلام الذي سيتم تنفيذه في قاعدة البيانات لحفظ المعلومات المدخلة في الحقول
            PreparedStatement ps;

            // إذا لم يقم المستخدم بوضع صورة للمنتج, سيتم تخزين فقط المعلومات التي أدخلها في الحقول
            ps = con.prepareStatement("SELECT * FROM BOOKS WHERE USERID = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            List<Book> books = new ArrayList();
//            while (rs.next()) {
//                books.add(new Book(rs.getInt("bookId"), rs.getString("name"), rs.getInt("numAuthor"), rs.getDate("addDate"), rs.getInt("bookNum"), rs.getDouble("price"), rs.getString("discribe"), rs.getInt("id")));
//            }
            return books;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public int buy(Book book, int numCopies) {
        if (this.money < book.getPrice() * numCopies) {
            return -1;
        }
        if (book.getTotalCopies()< numCopies) {
            return -2;
        }
        if (numCopies < 1) {
            return -3;
        }
        double total = book.getPrice() * numCopies;
        money -= total;
        book.setTotalCopies(book.getTotalCopies()- numCopies);
        User seller = book.getOwner();
        seller.addpurchase(book.getPrice());
        return numCopies;
    }

    public static void login(String email, String password) {
        User found = findByEmail(email);
        if (found == null) {
            return;
        }
        if (found.getPassword().equals(password)) {
            BookLibrary.auth(found);

        }
    }

}

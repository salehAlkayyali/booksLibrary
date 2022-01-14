package booklibrary;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Product {
 
    SimpleIntegerProperty id;
    SimpleStringProperty nameBook;
    SimpleStringProperty author;
    SimpleDoubleProperty price;
    SimpleStringProperty addDate;
    SimpleStringProperty image;
 
    public Product() {
        this.id = new SimpleIntegerProperty(0);
        this.nameBook = new SimpleStringProperty("");
        this.author = new SimpleStringProperty("");
        this.price = new SimpleDoubleProperty(0.0);
        this.addDate = new SimpleStringProperty("");
        this.image = new SimpleStringProperty("");
    }
 
    public Product(int id, String name,String author, double price, String addDate, String image) {
        this.id = new SimpleIntegerProperty(0);
        this.nameBook = new SimpleStringProperty(name);
        this.author = new SimpleStringProperty(author);
        this.price = new SimpleDoubleProperty(price);
        this.addDate = new SimpleStringProperty(addDate);
        this.image = new SimpleStringProperty(image);
    }
    


    public int getId() {
        return id.getValue();
    }
 
    public void setId(int id) {
        this.id = new SimpleIntegerProperty(id);
    }
 
    public String getName() {
        return nameBook.getValue();
    }
 
    public void setName(String name) {
        this.nameBook = new SimpleStringProperty(name);
    }
    public String getauthor() {
        return nameBook.getValue();
    }
 
    public void setauthor(String author) {
        this.nameBook = new SimpleStringProperty(author);
    }
 
    public double getPrice() {
        return price.getValue();
    }
 
    public void setPrice(double price) {
        this.price = new SimpleDoubleProperty(price);
    }
 
    public String getAddDate() {
        return addDate.getValue();
    }
 
    public void setAddedDate(String addDate) {
        this.addDate = new SimpleStringProperty(addDate);
    }
     
    public String getImage() {
        return image.getValue();
    }
 
    public void setImageUrl(String image) {
        this.image = new SimpleStringProperty(image);
    }
 
}

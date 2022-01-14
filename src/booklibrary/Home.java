/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package booklibrary;

import booklibrary.models.Book;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

/**
 *
 * @author HP
 */
public class Home implements Initializable, DBInfo {

    private Book selected;

    public Book getSelected() {
        return selected;
    }

    public void setSelected(Book selected) {
        this.selected = selected;
    }
    @FXML
    JFXButton addBook, update, delete, privateBooks, publicBooks;
    @FXML
    private AnchorPane rootPane;
    @FXML
    StackPane StackPane;
    @FXML
    VBox vbox;
    @FXML
    private ImageView image;
    @FXML
    public JFXTextField bookId, bookName, bookNum, bookPrice, totalBooks, addDate, author;

    @FXML
    private void addBook(ActionEvent event) throws IOException {
        addBook.setStyle("-fx-background-radius : 75px; -fx-background-color : #777;");
        StackAlert alert = new StackAlert("newBook.fxml", 1);
        StackPane.getChildren().add(alert.scene);
        alert.timeline.play();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        BookLibrary.home = this;
        image.setOnMouseClicked((MouseEvent event) -> {
            image.setImage(ImagePicker.pick());
            totalBooks.setText(bookId.getText());
        });
        BookLibrary.refresh();
        privateBooks.setOnMouseClicked((event) -> {
            privateBooks.setStyle("-fx-background-radius : 75px; -fx-background-color : #777;");
            publicBooks.setStyle("-fx-background-radius : 75px; -fx-background-color :  #A5A4A1;");
            BookLibrary.refresh();
        });
        publicBooks.setOnMouseClicked((event) -> {
            publicBooks.setStyle("-fx-background-radius : 75px; -fx-background-color : #777;");
            privateBooks.setStyle("-fx-background-radius : 75px; -fx-background-color :  #A5A4A1;");
            BookLibrary.refresh();
        });
        delete.setOnMouseClicked((event) -> {
            if (selected.getOwner().getId() == BookLibrary.getAdmin().getId()) {
                selected.delete();
                bookName.setText("");
                bookId.setText("");
                bookNum.setText("");
                bookPrice.setText("");
                addDate.setText("");
                author.setText("");
                BookLibrary.refresh();
            }
        });
        update.setOnMouseClicked((MouseEvent event) -> {
            selected.setName(bookName.getText());
            selected.setTotalCopies(Integer.parseInt(bookNum.getText()));
            selected.setPrice(Double.parseDouble(bookPrice.getText()));
            selected.setCreatedBy(Integer.parseInt(author.getText()));
            selected.update();
            BookLibrary.refresh();
        });
   }

    void unSelect() {
        vbox.getChildren().forEach((t) -> {
            t.setStyle("-fx-background-color :  #9C9B96; -fx-border-color : #9C9B96; -fx-border-width : 2 1 2 1");
        });
    }

}

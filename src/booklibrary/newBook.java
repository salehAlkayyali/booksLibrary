/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package booklibrary;

import booklibrary.models.Book;
import com.jfoenix.controls.JFXTextField;
import java.io.File;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.FileChooser;

public class newBook implements Initializable {

    public static ImageView defualt;
    @FXML
    private JFXTextField bookName, author, bookNum, bookPrice;
    @FXML
    private TextArea discribe;
    private String name;
    @FXML
    private Button chooseImageButton, save, delete, exit;
    @FXML
    private Label image;
    @FXML
    private StackPane stackPane;
    SpecialAlert alert = new SpecialAlert();
    File selectedFile = null;
    @FXML
    private AnchorPane rootPane;
    @FXML
    private Label title;

    private void resetWindow() {
        name = "defualt";
        bookName.setText("");
        author.setText("");
        bookNum.setText("");
        bookPrice.setText("");
        discribe.setText("");
        image.setGraphic(defualt);
        selectedFile = null;
        bookName.requestFocus();
    }

    private boolean checkInputs() {
        if (bookName.getText().equals("") && bookPrice.getText().equals("") && author.getText().equals("") && bookNum.getText().equals("")) {
            alert.show("الحقول المطلوبة فارغة", "لا يمكن أن تكون حقول الاسم والسعر والمؤلف والعدد فارغة!", Alert.AlertType.INFORMATION);
            return false;
        } else if (bookName.getText().equals("")) {
            alert.show("الحقل المطلوب فارغ", "يرجى إدخال اسم كتاب", Alert.AlertType.INFORMATION);
            return false;
        } else if (bookPrice.getText().equals("")) {
            alert.show("الحقل المطلوب فارغ", "يرجى إدخال سعر كتاب", Alert.AlertType.INFORMATION);
            return false;
        } else if (author.getText().equals("")) {
            alert.show("الحقل المطلوب فارغ", "يرجى إدخال مؤلف الكتاب", Alert.AlertType.INFORMATION);
            return false;
        } else if (bookNum.getText().equals("")) {
            alert.show("الحقل المطلوب فارغ", "يرجى إدخال عدد النسخ", Alert.AlertType.INFORMATION);
            return false;
        }

        try {
            Double.parseDouble(bookPrice.getText());
            return true;
        } catch (NumberFormatException ex) {
            alert.show("خطأ", "يجب أن يكون السعر رقمًا عشري ًا (على سبيل المثال: 40 ، 10.5)", Alert.AlertType.ERROR);
            return false;
        }
    }

    public static String chooseImage(String name, String bookName, Label image) {
        FileChooser fileChooser = new FileChooser();

        fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("Select a .JPG .PNG .GIF image", "*.jpg", "*.png", "*.gif")
        );

        String temp = bookName;
        name = FilesManager.getImage((bookName));
        if (name == null) {
            return name;
        }
        if (name.equals(temp)) {
            return name;
        }
        File selectedFile = FilesManager.getImageFile(name);
        if (selectedFile != null) {
            try {
                image.setText("");
                image.setGraphic(new ImageView(new Image(
                        selectedFile.toURI().toString(), 183, 183, true, true)));

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return name;
    }

    private void insertProduct() {
        if (checkInputs()) {
            Date sqlDate = new Date(System.currentTimeMillis());
            new Book(bookName.getText(),Integer.parseInt(author.getText()), sqlDate, Integer.parseInt(bookNum.getText()),Double.valueOf(bookPrice.getText()), discribe.getText(), BookLibrary.getAdmin().getId()).create();
            resetWindow();
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ImageView inner = new ImageView(new Image(getClass().getResource("/img/bookLogo.jpg").toExternalForm()));
        inner.setFitWidth(183);
        inner.setFitHeight(183);
        defualt = inner;
        image.setGraphic(inner);
        name = "defualt";
        bookName.requestFocus();

        chooseImageButton.setOnAction(Action -> {
            name = chooseImage(name, bookName.getText(), image);
        });

        save.setOnAction(Action -> {
            insertProduct();
            BookLibrary.refresh();
        });
        delete.setOnMouseClicked((event) -> {
            resetWindow();
        });
        exit.setOnMouseClicked((event) -> {
            stackPane.setVisible(false);
            BookLibrary.home.addBook.setStyle("-fx-background-radius : 75px; -fx-background-color :  #A5A4A1;");
        });
    }

}

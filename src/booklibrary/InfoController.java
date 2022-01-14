package booklibrary;

import booklibrary.models.Book;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

/**
 *
 * @author Saleh Alkayyali
 */
public class InfoController implements Initializable {

    @FXML
    Label image;
    @FXML
    JFXButton exit, save, chooseImageButton;
    @FXML
    JFXTextArea discribe;
    @FXML
    StackPane stackPane;
    private String name;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        exit.setOnMouseClicked((event) -> {
            stackPane.setVisible(false);
        });
    }

    public void bind(Book book) {
        if (book.getOwner().getId() != BookLibrary.getAdmin().getId()) {
            discribe.setDisable(true);
        }
        discribe.setText(book.getDescription());
//        if (FilesManager.getImageFile(book.getImage()).exists()) {
//            image.setGraphic(new ImageView(new Image(FilesManager.getImageFile(book.getImage()).toURI().toString(), 200, 200, true, true)));
//        }else {
//            image.setGraphic(newBook.defualt);
//        }
        save.setOnMouseClicked((event) -> {
            book.setDescription(discribe.getText());
//            book.setImage(name);
            book.update();
        });
        chooseImageButton.setOnMouseClicked((event) -> {
            name = newBook.chooseImage(name, book.getName(), image);
        });
    }

}

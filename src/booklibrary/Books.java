package booklibrary;

import booklibrary.models.Book;
import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Circle;

public class Books implements Initializable {

    @FXML
    public Label id, name, author, number, price, date, info;
    @FXML
    private Circle buy;
    @FXML
    private AnchorPane rootPane;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void bind(Book book) {
        this.id.setText(book.getId()+ "");
        this.name.setText(book.getName() + "");
        this.author.setText(book.getCreatedBy()+ "");
        this.number.setText(book.getTotalCopies()+ "");
        this.price.setText(book.getPrice() + "");
        this.date.setText(book.getCreatedAt()+ "");
        buy.setOnMouseClicked((event) -> {
            System.out.println("buy" + id);
        });
        rootPane.setOnMouseClicked((event) -> {
            BookLibrary.bookDetails(book);
        });
        info.setOnMouseClicked((event) -> {
            try {
                StackPane stackPane = BookLibrary.home.StackPane;
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/booklibrary/bookInformation.fxml"));
                Node n = loader.load();
                InfoController controller = loader.getController();
                controller.bind(book);
                stackPane.getChildren().add(n);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            
        });
    }

}

package booklibrary;

import booklibrary.models.Book;
import booklibrary.models.User;
import java.io.IOException;
import java.util.List;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class BookLibrary extends Application {

    public static Home home;
    private static User admin;
    private static Stage current;
    private static BookLibrary object;

    public static void auth(User found) {
        try {
            admin = found;
            current.close();
            current = new Stage();
            Parent Home = FXMLLoader.load(object.getClass().getResource("Home.fxml"));
            current.setScene(new Scene(Home));
            current.show();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

    public static User getAdmin() {
        return admin;
    }

    @Override
    public void start(Stage stage) throws Exception {
        object = this;
        current = stage;
        FilesManager.initialize();
        Parent root = FXMLLoader.load(getClass().getResource("register.fxml"));

        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.show();

    }

    public static void refresh() {
        home.vbox.getChildren().clear();
        List<Book> books;
        boolean publicBooks = home.privateBooks.getStyle().contains("#A5A4A1");
        if (publicBooks) {
            books = Book.findAll();
        } else {
            books = BookLibrary.getAdmin().findAllBooks();
        }
        for (Book book : books) {
            if (!publicBooks || book.getOwner().getId() != admin.getId()) {
                try {
                    FXMLLoader loader = new FXMLLoader(home.getClass().getResource("/booklibrary/Books.fxml"));
                    Node node = loader.load();
                    Books controller = (Books) loader.getController();
                    controller.bind(book);
                    home.vbox.getChildren().add(node);
                    EventHandler<? super MouseEvent> oldEvent = node.getOnMouseClicked();
                    node.setOnMouseClicked((event) -> {
                        oldEvent.handle(event);
                        home.unSelect();
                        node.setStyle("-fx-background-color :  #9C9B96; -fx-border-color : #333; -fx-border-width : 2 1 2 1");
                    });
                    VBox.setMargin(node, new Insets(0, 0, 3, 0));
                } catch (IOException ex) {
                }
            }
        }
        boolean isMine = publicBooks;
        home.bookName.setDisable(isMine);
        home.bookNum.setDisable(isMine);
        home.bookPrice.setDisable(isMine);
        home.author.setDisable(isMine);
    }

    public static void bookDetails(Book book) {
        home.bookId.setDisable(true);
        home.addDate.setDisable(true);
        home.bookId.setText(book.getId()+ "");
        home.bookName.setText(book.getName());
        home.author.setText(book.getCreatedBy()+"");
        home.bookNum.setText(book.getTotalCopies()+ "");
        home.bookPrice.setText(book.getPrice() + "");
        home.addDate.setText(book.getCreatedAt()+ "");
        home.setSelected(book);

    }

    public static void main(String[] args) {
        launch(args);

    }

}

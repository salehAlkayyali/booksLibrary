package booklibrary;

import java.io.File;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class ImagePicker {

    public static Image pick() {
        return new Image(pickFile().toURI().toString());
        
    }
    
    public static File pickFile() {
        FileChooser chooser = new FileChooser();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Image Files", "*.jpg", "*.jpeg", "*.png");
        chooser.getExtensionFilters().add(extFilter);
        File img = chooser.showOpenDialog(new Stage());
        return img;
        
    }
}

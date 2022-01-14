package booklibrary;

import java.io.IOException;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.util.Duration;

public class StackAlert {

    public Timeline timeline;
    public Parent scene;

    public StackAlert(String path, int time) throws IOException {
        Parent node = FXMLLoader.load(getClass().getResource(path));
        scene = node;
        Scene scene = new Scene(node);
        node.translateYProperty().set(scene.getHeight());
        Timeline timeline = new Timeline();
        KeyValue keyValue = new KeyValue(node.translateYProperty(), 0, Interpolator.EASE_IN);
        KeyFrame keyFrame = new KeyFrame(Duration.seconds(time), keyValue);
        timeline.getKeyFrames().add(keyFrame);
        this.timeline = timeline;
    }

}

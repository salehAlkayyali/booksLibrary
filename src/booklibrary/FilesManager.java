package booklibrary;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class FilesManager {

    private final static String PATH = System.getProperty("user.home") + "\\Library\\";

    public static void initialize() {
        File file = new File(PATH);
        if (!file.exists()) {
            file.mkdir();
        }
        File imageFolder = new File(PATH + "image");
        if (!imageFolder.exists()) {
            imageFolder.mkdir();
        }
    }

    public static File getImageFile(String path) {
        return new File(PATH + "image\\" + path);
    }
    
    
    public static String getImage(String name) {
        String temp = name;
        try {
            File image = ImagePicker.pickFile();
            if (image == null) {
                return temp;
            }
            name += image.getName();
            File file = getImageFile(name);
            file.delete();
            File copy = getImageFile(name);
            Files.copy(image.toPath(), copy.toPath());
            return name;
        } catch (IOException ex) {
            ex.printStackTrace();
            return temp;
        }
    }
}

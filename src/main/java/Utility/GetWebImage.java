package Utility;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class GetWebImage {
    public static ImageView getImage(String imageUrl, double width, double height) {

        if(imageUrl == null) {
            return getImagePlaceholder();
        }

        Image image = new Image(imageUrl, width, height, true, true, true);

        if(image.isError()) {
            try {
                image = new Image(new FileInputStream("src/main/resources/Images/placeholder.jpg"), width, height, true, true);
            } catch (FileNotFoundException e) {
                image = null;
            }
        }

        return new ImageView(image);
    }

    public static ImageView getImagePlaceholder() {
        try {
            return new ImageView(new Image(new FileInputStream("src/main/resources/Images/placeholder.jpg")));
        } catch (FileNotFoundException e) {
            return null;
        }
    }
}

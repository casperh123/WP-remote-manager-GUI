package Utility;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class WebUtilities {

    private static final String PLACEHOLDER_IMAGE_PATH = "src/main/resources/Images/placeholder.jpg";
    private static final Logger LOGGER = Logger.getLogger(WebUtilities.class.getName());

    private WebUtilities() {
        // private constructor to prevent instantiation
    }

    public static String sanitizeHtml(String html) {
        return (html == null || html.isEmpty()) ? "-" : html.replaceAll("<[^>]*>", "");
    }

    public static ImageView getImageView(String imageUrl, double width, double height) {
        Image image = (imageUrl != null) ? new Image(imageUrl, width, height, true, true, true) : null;

        if (image == null || image.isError()) {
            image = loadImageFromFile(PLACEHOLDER_IMAGE_PATH, width, height);
        }

        return new ImageView(image);
    }

    public static ImageView getImagePlaceholder() {
        Image image = loadImageFromFile(PLACEHOLDER_IMAGE_PATH);
        return new ImageView(image);
    }

    private static Image loadImageFromFile(String path, double width, double height) {
        try {
            return new Image(new FileInputStream(path), width, height, true, true);
        } catch (FileNotFoundException e) {
            LOGGER.log(Level.WARNING, "Failed to load image from file", e);
            return null;
        }
    }

    private static Image loadImageFromFile(String path) {
        try {
            return new Image(new FileInputStream(path));
        } catch (FileNotFoundException e) {
            LOGGER.log(Level.WARNING, "Failed to load image from file", e);
            return null;
        }
    }
}

package GUI.AddWebsite;

import Website.Website;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.util.List;

public class AddWebsiteView extends VBox {

    private AddWebsiteController addWebsiteController;

    public AddWebsiteView(List<Website> websites) {
        this.addWebsiteController = new AddWebsiteController(websites);
    }


}

package GUI.ComponentPages;

import Components.Product.Product;
import GUI.Components.StateButton;
import javafx.geometry.Insets;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.web.HTMLEditor;

public class ProductPage extends GridPane {

    private Product product;
    private VBox saveControlsContainer;
    private ComboBox<String> statusDropDown;
    private Label timeDateCreated;
    private StateButton saveButton;
    private VBox mainContainer;
    private TextField name;
    private Label permalink;
    private HTMLEditor description;

    public ProductPage(Product product) {
        this.product = product;

        setContent();
        setStyling();
        setEventListeners();
    }

    public ProductPage() {
        this.product = null;

        setContent();
        setStyling();
        setEventListeners();
    }

    private void setContent() {
        setMainContainerContent();
        setSaveControlsContent();

    }

    private void setMainContainerContent() {
        mainContainer = new VBox();
        name = new TextField(product.getName());
        permalink = new Label(product.getPermalink());
        description = new HTMLEditor();

        description.setHtmlText(product.getDescription());

        mainContainer.getChildren().addAll(name, permalink, description);

        GridPane.setColumnIndex(mainContainer, 0);

        this.getChildren().add(mainContainer);
    }

    private void setSaveControlsContent() {

        saveControlsContainer = new VBox();
        statusDropDown = new ComboBox<>();
        timeDateCreated = new Label(product.getDateCreated());

        if(product == null) {
            saveButton = new StateButton("Publish");
        } else {
            saveButton = new StateButton("Update");
        }

        statusDropDown.getItems().addAll("Published", "Private", "Pending Review", "Draft");

        switch(product.getStatus()) {
            case "draft" -> {
                statusDropDown.setValue("Draft");
            }
            case "pending" -> {
                statusDropDown.setValue("Pending Review");
            }
            case "private" -> {
                statusDropDown.setValue("Private");
            }
            case "publish" -> {
                statusDropDown.setValue("Published");
            }
        }

        saveControlsContainer.getChildren().addAll(statusDropDown, timeDateCreated, saveButton);

        GridPane.setColumnIndex(saveControlsContainer, 1);

        this.getChildren().add(saveControlsContainer);
    }

    private void setStyling(){
        ColumnConstraints mainColumnConstraint = new ColumnConstraints();
        ColumnConstraints secondaryColumnConstraint = new ColumnConstraints();

        mainColumnConstraint.setPercentWidth(80);
        secondaryColumnConstraint.setPercentWidth(20);

        this.getColumnConstraints().add(mainColumnConstraint);
        this.getColumnConstraints().add(secondaryColumnConstraint);

        this.setHgap(10);
        this.setVgap(10);

        setMainContainerStyling();
        setSaveControlsContainerStyling();
    }

    private void setMainContainerStyling() {
        mainContainer.setSpacing(10);
        mainContainer.setPadding(new Insets(20));
    }

    private void setSaveControlsContainerStyling() {
        saveControlsContainer.setSpacing(10);
        saveControlsContainer.setPadding(new Insets(20));
        setBackground(new Background(new BackgroundFill(Color.rgb(0, 0, 0, 0.1), new CornerRadii(5), new Insets(10))));
    }

    private void setEventListeners() {

    }

    private void saveComponent() {

    }
}

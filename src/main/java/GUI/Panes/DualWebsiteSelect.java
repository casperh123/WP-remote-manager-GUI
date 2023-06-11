package GUI.Panes;

import GUI.Cards.WebsiteCard;
import GUI.Components.PrimaryButton;
import Website.Website;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.util.List;

public abstract class DualWebsiteSelect extends VBox {

    private Text title;
    private Text mainWebsiteNotice;
    protected Text comparingWebsiteNotice;
    protected TextArea missingProductsText;
    protected Website mainWebsite;
    protected Website comparingWebsite;
    private boolean mainWebsiteSelected;
    private List<Website> websites;
    private Button clearButton;

    public DualWebsiteSelect(String title, List<Website> websites) {
        this.title = new Text(title);
        this.websites = websites;
        this.mainWebsiteNotice = new Text();
        this.comparingWebsiteNotice = new Text();
        this.missingProductsText = new TextArea();
        this.clearButton = new PrimaryButton("Clear");

        this.getChildren().add(this.title);
        this.getChildren().add(mainWebsiteNotice);
        this.getChildren().add(comparingWebsiteNotice);
        generateWebsiteCards();
        this.getChildren().add(clearButton);
        this.setSpacing(10.0);
        this.setPadding(new Insets(10));

        this.title.setFont(Font.font(20));

        setEventHandlers();
    }

    public void generateWebsiteCards() {
        for(Website website : websites) {

            WebsiteCard websiteCard = new WebsiteCard(website);

            websiteCard.setOnMouseClicked(e -> {
                if(!mainWebsiteSelected) {
                    mainWebsite = websiteCard.getWebsite();
                    mainWebsiteSelected = !mainWebsiteSelected;
                    mainWebsiteNotice.setText("Main Website: " + mainWebsite.getName());
                } else {
                    comparingWebsite = websiteCard.getWebsite();
                    comparingWebsiteNotice.setText("Comparing Website: " + comparingWebsite.getName());
                }
            });

            this.getChildren().add(websiteCard);
        }
    }

    private void setEventHandlers() {
        clearButton.setOnMouseClicked(e -> {
            mainWebsite = null;
            comparingWebsite = null;
            mainWebsiteSelected = false;

            missingProductsText.clear();

            mainWebsiteNotice.setText("Main website: ");
            comparingWebsiteNotice.setText("Comparing Website:");
        });
    }

    abstract protected void search();
}

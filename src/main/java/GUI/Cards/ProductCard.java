package GUI.Cards;

import Components.Product.Product;
import Components.Product.ProductComponents.ProductCategory;
import GUI.ComponentPages.ProductPage;
import GUI.Components.CopyableText;
import GUI.GlobalState.GlobalComponents;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class ProductCard extends BaseCard {

    private ImageView productImage;
    private CopyableText productTitle;
    private CopyableText productSku;
    private Label stockStatus;
    private Label price;
    private Label categories;
    private Label status;
    private CopyableText dateCreated;
    private VBox statusTimeWrapper;
    private String currencyCode;
    private Product product;

    public ProductCard(final Product product, final String currencyCode) {
        this.product = product;
        this.currencyCode = currencyCode;

        productImage = product.getImages().size() > 0 ? new ImageView(product.getFeaturedImage().getImage()) : new ImageView();

        setContent();
        setStyle();
        setEventListeners();
    }


    @Override
    protected void setContent() {
        productTitle = new CopyableText(product.getName());
        productSku = new CopyableText(product.getSku());
        price = new Label(product.getRegularPrice() + " " + currencyCode);
        status = new Label(product.getFormattedStatus());
        dateCreated = new CopyableText(product.getFormattedDateCreated());
        statusTimeWrapper = new VBox();

        statusTimeWrapper.getChildren().addAll(status, dateCreated);

        if(product.getStockStatus().equals("outofstock")) {
            stockStatus = new Label("Out of stock ");
            stockStatus.setTextFill(Color.rgb(177, 68, 110));
        } else {
            stockStatus = new Label("In stock: " + product.getStockQuantity());
            stockStatus.setTextFill(Color.rgb(122, 211, 124));
        }

        StringBuilder categoryString = new StringBuilder();

        for(ProductCategory category : product.getCategories()) {
            categoryString.append(category).append(", ");
        }

        categories = new Label(categoryString.toString());

        this.getChildren().addAll(productImage, productTitle, productSku, stockStatus, price, categories, statusTimeWrapper);
    }

    @Override
    protected void setEventListeners() {
        this.setOnMouseClicked(e -> {
            GlobalComponents.getInstance().setMainContent(new ProductPage(product));
        });
    }

    @Override
    protected void setStyle() {
        this.setAlignment(Pos.CENTER_LEFT);

        setGridConstraints();
        setColumnConstraints();

        this.setHgap(10);

        statusTimeWrapper.setAlignment(Pos.CENTER_LEFT);

        productTitle.setFont(new Font(18));
        productTitle.setWrapText(true);
        productTitle.setTextMaxWidth(200);

        categories.setMaxWidth(150);
        categories.setWrapText(true);

        productImage.setFitHeight(100);
        productImage.setFitWidth(100);
    }

    public void setGridConstraints() {
        GridPane.setConstraints(productImage, 0, 0);
        GridPane.setConstraints(productTitle, 1, 0);
        GridPane.setConstraints(productSku, 2, 0);
        GridPane.setConstraints(stockStatus, 3, 0);
        GridPane.setConstraints(price, 4, 0);
        GridPane.setConstraints(categories, 5, 0);
        GridPane.setConstraints(statusTimeWrapper, 6, 0);
    }

    public void setColumnConstraints() {
        this.getColumnConstraints().addAll(
                new ColumnConstraints(100),
                new ColumnConstraints(250),
                new ColumnConstraints(100),
                new ColumnConstraints(100),
                new ColumnConstraints(80),
                new ColumnConstraints(200)
        );
    }

    public Product getProduct() {
        return product;
    }
}

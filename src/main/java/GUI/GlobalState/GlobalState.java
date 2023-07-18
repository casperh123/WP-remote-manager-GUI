package GUI.GlobalState;

import Exceptions.BadHTTPResponseException;
import Exceptions.FetchException;
import GUI.PaneHistory.PaneHistory;
import GUI.PaneHistory.StateButton.StateButton;
import GUI.Panes.OrderListPane;
import GUI.Panes.ProductListPane;
import Website.Website;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.List;

public class GlobalState {

    private static Website activeWebsite;
    private static String listState;
    private static StateButton productsButton;
    private static StateButton ordersButton;
    private static ScrollPane mainScrollPane;
    private static Pane mainContent;

    static {
        listState = "products";

        mainContent = new Pane(new Text("No Text Selected"));
        mainScrollPane = new ScrollPane(mainContent);

        productsButton = new StateButton("Products");
        ordersButton = new StateButton("Orders");

        setEventListeners();
    }

    private static void setEventListeners() {
        productsButton.loadSetOnMouseClicked(e -> {
            GlobalState.setListProducts();
            try {
                mainContent = GlobalState.getListPane();
                mainScrollPane.setContent(mainContent);
                PaneHistory.getInstance().addPane(mainContent);
            } catch (BadHTTPResponseException | FetchException ex) {
                throw new RuntimeException(ex);
            }
        });

        ordersButton.loadSetOnMouseClicked(e -> {
            GlobalState.setListOrders();
            try {
                mainContent = GlobalState.getListPane();
                mainScrollPane.setContent(mainContent);
                PaneHistory.getInstance().addPane(mainContent);
            } catch (BadHTTPResponseException | FetchException ex) {
                throw new RuntimeException(ex);
            }
        });
    }

    public static ScrollPane getMainScrollPane() {
        return mainScrollPane;
    }

    public static void setMainContent(Pane mainContent) {
        GlobalState.mainContent = mainContent;
        mainScrollPane.setContent(mainContent);
    }

    public static Pane getMainContent() {
        return mainContent;
    }


    public static Pane getListPane() throws BadHTTPResponseException, FetchException {
        switch(listState) {
            case "products" -> {
                return new ProductListPane(activeWebsite.getProducts());
            }
            case "orders" -> {
                return new OrderListPane(activeWebsite.getOrders());
            }
        }
        return new Pane(new Text("No Website Selected"));
    }

    public static List<Button> getGlobalButtons() {
        List<Button> buttonList = new ArrayList<>();

        buttonList.add(productsButton);
        buttonList.add(ordersButton);

        return buttonList;
    }

    public static Website getActiveWebsite() {
        return activeWebsite;
    }

    public static void setActiveWebsite(Website website) throws BadHTTPResponseException, FetchException {
        activeWebsite = website;
        mainContent = getListPane();
        mainScrollPane.setContent(mainContent);
    }

    public static void setListProducts() {
        listState = "products";
    }

    public static void setListOrders() { listState = "orders"; }
}

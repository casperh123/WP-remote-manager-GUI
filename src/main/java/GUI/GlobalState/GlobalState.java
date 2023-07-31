package GUI.GlobalState;

import Exceptions.BadHTTPResponseException;
import Exceptions.FetchException;
import GUI.PaneHistory.PaneHistory;
import GUI.Components.StateButton;
import GUI.Panes.CategoryListPane;
import GUI.Panes.OrderListPane;
import GUI.Panes.ProductListPane;
import GUI.Panes.TagListPane;
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
    private static StateButton categoriesButton;
    private static StateButton tagsButton;
    private static ScrollPane mainScrollPane;
    private static Pane mainContent;

    static {
        listState = "products";

        mainContent = new Pane(new Text("No Text Selected"));
        mainScrollPane = new ScrollPane(mainContent);

        productsButton = new StateButton("Products");
        ordersButton = new StateButton("Orders");
        categoriesButton = new StateButton("Categories");
        tagsButton = new StateButton("Tags");

        productsButton.setActive();
        ordersButton.setActive();
        categoriesButton.setActive();
        tagsButton.setActive();

        setEventListeners();
    }

    private static void setEventListeners() {
        productsButton.loadSetOnMouseClicked(e -> {
            GlobalState.setListProducts();
            try {
                Pane newMainContent = GlobalState.getListPane();
                setMainContent(newMainContent);
            } catch (BadHTTPResponseException | FetchException ex) {
                throw new RuntimeException(ex);
            }
        });

        ordersButton.loadSetOnMouseClicked(e -> {
            GlobalState.setListOrders();
            try {
                Pane newMainContent = GlobalState.getListPane();
                setMainContent(newMainContent);
            } catch (BadHTTPResponseException | FetchException ex) {
                throw new RuntimeException(ex);
            }
        });

        categoriesButton.loadSetOnMouseClicked(e -> {
            GlobalState.setListCategories();
            try {
                Pane newMainContent = GlobalState.getListPane();
                setMainContent(newMainContent);
            } catch (BadHTTPResponseException | FetchException ex) {
                throw new RuntimeException(ex);
            }
        });

        tagsButton.loadSetOnMouseClicked(e -> {
            GlobalState.setListTags();
            try {
                Pane newMainContent = GlobalState.getListPane();
                setMainContent(newMainContent);
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
        PaneHistory.getInstance().addPane(GlobalState.getMainContent());
    }

    public static void setMainContentNoCache(Pane mainContent) {
        GlobalState.mainContent = mainContent;
        mainScrollPane.setContent(mainContent);
    }

    public static Pane getMainContent() {
        return mainContent;
    }

    public static Pane getListPane() throws BadHTTPResponseException, FetchException {
        switch(listState) {
            case "products" -> {
                return new ProductListPane(activeWebsite.getAllProducts(), activeWebsite.getCurrency());
            }
            case "orders" -> {
                return new OrderListPane(activeWebsite.getOrders());
            }
            case "categories" -> {
                return new CategoryListPane(activeWebsite.getCategories());
            }
            case "tags" -> {
                return new TagListPane(activeWebsite.getTags());
            }
        }
        return new Pane(new Text("No Website Selected"));
    }

    public static List<Button> getGlobalButtons() {
        List<Button> buttonList = new ArrayList<>();

        buttonList.add(productsButton);
        buttonList.add(ordersButton);
        buttonList.add(categoriesButton);
        buttonList.add(tagsButton);

        return buttonList;
    }

    public static Website getActiveWebsite() {
        return activeWebsite;
    }

    public static void setActiveWebsite(Website website) throws BadHTTPResponseException, FetchException {
        activeWebsite = website;
        mainContent = getListPane();
        setMainContent(mainContent);
    }

    public static void setListProducts() {
        listState = "products";
    }

    public static void setListOrders() { listState = "orders"; }

    public static void setListCategories() { listState = "categories"; }

    public static void setListTags() { listState = "tags"; }
}


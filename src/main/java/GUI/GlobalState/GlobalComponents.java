package GUI.GlobalState;

import Exceptions.BadHTTPResponseException;
import Exceptions.FetchException;
import GUI.Components.StateButton;
import GUI.Enums.State;
import GUI.PaneHistory.PaneHistory;
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

public class GlobalComponents {

    private static GlobalComponents instance;
    private StateButton productsButton;
    private StateButton ordersButton;
    private StateButton categoriesButton;
    private StateButton tagsButton;
    private ScrollPane mainScrollPane;
    private Pane mainContent;

    private GlobalComponents() {

        mainContent = new Pane(new Text("No Text Selected"));
        mainScrollPane = new ScrollPane(mainContent);

        productsButton = new StateButton("Products");
        ordersButton = new StateButton("Orders");
        categoriesButton = new StateButton("Categories");
        tagsButton = new StateButton("Tags");

        productsButton.setBackgroundState(State.ACTIVE);
        ordersButton.setBackgroundState(State.ACTIVE);
        categoriesButton.setBackgroundState(State.ACTIVE);
        tagsButton.setBackgroundState(State.ACTIVE);

        setEventListeners();
    }

    public ScrollPane getMainScrollPane() {
        return mainScrollPane;
    }

    public void getComponentList() throws BadHTTPResponseException, FetchException {
        mainContent = getList(GlobalState.getState());
        setMainContent(mainContent);
    }

    public void setMainContent(Pane mainContent) {
        this.mainContent = mainContent;
        mainScrollPane.setContent(mainContent);
        PaneHistory.getInstance().addPane(getMainContent());
    }

    public void setMainContentNoCache(Pane mainContent) {
        this.mainContent = mainContent;
        mainScrollPane.setContent(mainContent);
    }

    public Pane getMainContent() {
        return mainContent;
    }

    private void setEventListeners() {
        productsButton.loadSetOnMouseClicked(e -> {
            updateFromState(GlobalStates.PRODUCTS);
        });

        ordersButton.loadSetOnMouseClicked(e -> {
            updateFromState(GlobalStates.ORDERS);
        });

        categoriesButton.loadSetOnMouseClicked(e -> {
            updateFromState(GlobalStates.CATEGORIES);
        });

        tagsButton.loadSetOnMouseClicked(e -> {
            updateFromState(GlobalStates.TAGS);
        });
    }

    private void updateFromState(GlobalStates state) {
        GlobalState.setState(state);
        try {
            getComponentList();
        } catch (BadHTTPResponseException | FetchException ex) {
            throw new RuntimeException(ex);
        }
    }

    public List<Button> getGlobalButtons() {
        List<Button> buttonList = new ArrayList<>();

        buttonList.add(productsButton);
        buttonList.add(ordersButton);
        buttonList.add(categoriesButton);
        buttonList.add(tagsButton);

        return buttonList;
    }

    public static GlobalComponents getInstance() {
        if(instance == null) {
            instance = new GlobalComponents();
        }
        return instance;
    }

    public static Pane getList(GlobalStates state) throws BadHTTPResponseException, FetchException {

        Website activeWebsite = GlobalState.getActiveWebsite();

        return switch (state) {
            case PRODUCTS -> new ProductListPane(activeWebsite.getAllProducts(), activeWebsite.getCurrency());
            case ORDERS -> new OrderListPane(activeWebsite.getOrders());
            case CATEGORIES -> new CategoryListPane(activeWebsite.getCategories());
            case TAGS -> new TagListPane(activeWebsite.getTags());
            default -> new Pane(new Text("No Website Selected"));
        };
    }
}

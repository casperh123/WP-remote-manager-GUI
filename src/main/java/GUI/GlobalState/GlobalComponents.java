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
            GlobalState.setState(GlobalStates.PRODUCTS);
            try {
                Pane newMainContent = getListPane(GlobalState.getState());
                setMainContent(newMainContent);
            } catch (BadHTTPResponseException | FetchException ex) {
                throw new RuntimeException(ex);
            }
        });

        ordersButton.loadSetOnMouseClicked(e -> {
            GlobalState.setState(GlobalStates.ORDERS);
            try {
                Pane newMainContent = getListPane(GlobalState.getState());
                setMainContent(newMainContent);
            } catch (BadHTTPResponseException | FetchException ex) {
                throw new RuntimeException(ex);
            }
        });

        categoriesButton.loadSetOnMouseClicked(e -> {
            GlobalState.setState(GlobalStates.CATEGORIES);
            try {
                Pane newMainContent = getListPane(GlobalState.getState());
                setMainContent(newMainContent);
            } catch (BadHTTPResponseException | FetchException ex) {
                throw new RuntimeException(ex);
            }
        });

        tagsButton.loadSetOnMouseClicked(e -> {
            GlobalState.setState(GlobalStates.TAGS);
            try {
                Pane newMainContent = getListPane(GlobalState.getState());
                setMainContent(newMainContent);
            } catch (BadHTTPResponseException | FetchException ex) {
                throw new RuntimeException(ex);
            }
        });
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

    public void getComponentList() throws BadHTTPResponseException, FetchException {
        mainContent = getListPane(GlobalState.getState());
        setMainContent(mainContent);
    }

    public static Pane getListPane(GlobalStates state) throws BadHTTPResponseException, FetchException {

        Website activeWebsite = GlobalState.getActiveWebsite();

        switch(state) {
            case PRODUCTS -> {
                return new ProductListPane(activeWebsite.getAllProducts(), activeWebsite.getCurrency());
            }
            case ORDERS -> {
                return new OrderListPane(activeWebsite.getOrders());
            }
            case CATEGORIES -> {
                return new CategoryListPane(activeWebsite.getCategories());
            }
            case TAGS -> {
                return new TagListPane(activeWebsite.getTags());
            }
        }
        return new Pane(new Text("No Website Selected"));
    }
}

package GUI.GlobalState;

import Exceptions.BadHTTPResponseException;
import Exceptions.FetchException;
import Website.Website;

public class GlobalState {

    private static Website activeWebsite;
    private static GlobalStates state;

    static {
        state = GlobalStates.PRODUCTS;
    }

    public static GlobalStates getState() {
        return state;
    }

    public static void setState(GlobalStates state) {
        GlobalState.state = state;
    }

    public static Website getActiveWebsite() {
        return activeWebsite;
    }

    public static void setActiveWebsite(Website website) throws BadHTTPResponseException, FetchException {
        activeWebsite = website;
        GlobalComponents.getInstance().getComponentList();
    }
}


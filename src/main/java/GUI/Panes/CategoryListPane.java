package GUI.Panes;

import Components.Category.Category;
import Components.Interfaces.ID;
import GUI.Cards.CategoryCard;
import Lists.QueryList;

public class CategoryListPane extends ListPane {

    public CategoryListPane(QueryList<Category> categoriesList) {
        super(categoriesList);
    }

    @Override
    void renderList() {

        if(componentList == null) {
            return;
        }

        listContainer.getChildren().clear();

        for(ID component : componentList) {

            Category category = (Category) component;

            listContainer.getChildren().add(new CategoryCard(category));
        }
    }
}

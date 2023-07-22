package GUI.Panes;

import Components.Interfaces.ID;
import Components.Product.ProductComponents.Tag;
import GUI.Cards.ListCard;
import GUI.Cards.TagCard;
import Lists.QueryList;
import javafx.geometry.Insets;

public class TagListPane extends ListPane {

    public TagListPane(QueryList<Tag> tagList) {
        super(tagList);

        this.setSpacing(10);
        this.setPadding(new Insets(10));

        renderList();
    }

    @Override
    void renderList() {

        this.getChildren().clear();

        if(componentList == null) {
            return;
        }

        for(ID component : componentList) {

            Tag tag = (Tag) component;

            this.getChildren().add(new TagCard(tag));
        }
    }
}

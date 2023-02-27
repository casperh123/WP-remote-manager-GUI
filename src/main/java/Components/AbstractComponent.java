package Components;

import Components.Interfaces.ID;
import com.jsoniter.annotation.JsonCreator;

public abstract class AbstractComponent implements ID {
    @Override
    public String toString() {
        return "AbstractComponent{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    protected int id;
    protected String name;

    @JsonCreator
    public AbstractComponent() {
    }

    public AbstractComponent(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}

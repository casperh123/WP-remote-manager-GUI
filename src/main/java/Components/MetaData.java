package Components;

import Components.Interfaces.ID;
import com.jsoniter.annotation.JsonCreator;

public class MetaData implements ID {

    //read-only
    private int id;

    private String key;
    private String value;

    @JsonCreator()
    public MetaData() {
    }

    public MetaData(int id, String key, String value) {
        this.id = id;
        this.key = key;
        this.value = value;
    }

    public int getId() {
        return id;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}

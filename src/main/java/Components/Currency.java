package Components;

import com.jsoniter.annotation.JsonCreator;

import java.io.Serializable;

public class Currency implements Serializable {

    private String code;
    private String name;
    private String symbol;

    @JsonCreator
    public Currency() {}

    public Currency(String code, String name, String symbol) {
        this.code = code;
        this.name = name;
        this.symbol = symbol;
    }


    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public String getSymbol() {
        return symbol;
    }
}

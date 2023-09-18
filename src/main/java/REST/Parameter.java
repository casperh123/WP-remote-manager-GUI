package REST;

public class Parameter {
    String parameter;
    String value;

    public Parameter(String parameter, String value) {
        this.parameter = parameter;
        this.value = value;
    }

    public String getParameter() { return parameter; }
    public String getValue() { return value; }

    public String toString() { return parameter + ": " + value; }
}

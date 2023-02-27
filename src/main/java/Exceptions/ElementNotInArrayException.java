package Exceptions;

public class ElementNotInArrayException extends Exception {

    public ElementNotInArrayException(String element) {
        super("Element not found in list: " + element);
    }

}

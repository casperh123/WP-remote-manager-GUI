package Exceptions;

public class DataNotInArrayException extends Exception {
    private String messageData;

    public DataNotInArrayException(String data) {
        this.messageData = data;
    }

    public String toString() {
        return messageData + " Not in in array";
    }
}

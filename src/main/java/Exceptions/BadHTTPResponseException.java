package Exceptions;

import java.io.IOException;

public class BadHTTPResponseException extends IOException {
    private final int responseCode;
    private final String errorMessage;

    public BadHTTPResponseException(int responseCode) {
        this.responseCode = responseCode;
        this.errorMessage = "Invalid HTTP response: ";
    }

    public BadHTTPResponseException(String message) {
        this.errorMessage = message;
        this.responseCode = -1;
    }

    public String getMessage() {

        if(responseCode == -1) {
            return errorMessage;
        }
        return errorMessage + responseCode;
    }
}

package id.gpay.technical.utility.exception;

import org.springframework.http.HttpStatus;

public class CustomValidationException extends RuntimeException {
    private HttpStatus status;
    private String message;

    public CustomValidationException(HttpStatus status, String message) {
        super();
        this.status = status;
        this.message = message;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

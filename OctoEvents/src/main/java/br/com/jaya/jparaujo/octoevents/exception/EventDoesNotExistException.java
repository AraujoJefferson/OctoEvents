package br.com.jaya.jparaujo.octoevents.exception;

public class EventDoesNotExistException extends RuntimeException {
    public EventDoesNotExistException() {
        super();
    }

    public EventDoesNotExistException(String message) {
        super(message);
    }

    public EventDoesNotExistException(String message, Throwable cause) {
        super(message, cause);
    }

    public EventDoesNotExistException(Throwable cause) {
        super(cause);
    }
}

package br.com.jaya.jparaujo.octoevents.exception;

public class IssueDoesNotExistException extends RuntimeException {
    public IssueDoesNotExistException() {
        super();
    }

    public IssueDoesNotExistException(String message) {
        super(message);
    }

    public IssueDoesNotExistException(String message, Throwable cause) {
        super(message, cause);
    }

    public IssueDoesNotExistException(Throwable cause) {
        super(cause);
    }
}

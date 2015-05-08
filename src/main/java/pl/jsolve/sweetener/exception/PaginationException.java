package pl.jsolve.sweetener.exception;

public class PaginationException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public PaginationException(String message) {
        super(message);
    }
}

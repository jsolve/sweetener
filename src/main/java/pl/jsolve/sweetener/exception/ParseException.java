package pl.jsolve.sweetener.exception;

public class ParseException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public ParseException(Exception ex) {
        super(ex);
    }
}

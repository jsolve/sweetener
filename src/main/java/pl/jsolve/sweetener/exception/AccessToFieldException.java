package pl.jsolve.sweetener.exception;

public class AccessToFieldException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public AccessToFieldException(String message) {
	super(message);
    }
}

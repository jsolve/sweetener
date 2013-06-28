package pl.jsolve.sweetener.exception;

@SuppressWarnings("serial")
public class InstanceCreationException extends RuntimeException {

    public InstanceCreationException(String message, Throwable cause) {
	super(message, cause);
    }
}
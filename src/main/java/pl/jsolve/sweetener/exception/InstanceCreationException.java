package pl.jsolve.sweetener.exception;

public class InstanceCreationException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public InstanceCreationException(String message, Throwable cause) {
		super(message, cause);
	}
}
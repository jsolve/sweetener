package pl.jsolve.sweetener.exception;

@SuppressWarnings("serial")
public class BuilderWithMethodBindingException extends RuntimeException {

    public BuilderWithMethodBindingException(Exception e, String fieldName, String fieldValue) {
	super("Could not set field " + fieldName + " to value " + fieldValue, e);
    }
}

package pl.jsolve.sweetener.mapper.exception;

public class AnnotationDrivenMapperException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public AnnotationDrivenMapperException(String message) {
		super(message);
	}

	public AnnotationDrivenMapperException(String format, Object... args) {
		super(String.format(format, args));
	}
}

package pl.jsolve.sweetener.mapper.annotationdriven.exception;

public class MappingException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public MappingException(String message) {
		super(message);
	}

	public MappingException(String format, Object... args) {
		super(String.format(format, args));
	}

	public MappingException(Throwable e, String format, Object... args) {
		super(String.format(format, args), e);
	}
}

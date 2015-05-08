package pl.jsolve.sweetener.exception;

public class OutOfRangeException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private Range range;

    public OutOfRangeException(Exception ex, Range range) {
        super(ex);
        this.range = range;
    }

    public OutOfRangeException(String message) {
        super(message);
    }

    public Range getRange() {
        return range;
    }

    public static enum Range {
        MIN, MAX
    }

}

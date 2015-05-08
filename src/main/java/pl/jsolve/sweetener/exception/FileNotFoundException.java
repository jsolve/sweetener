package pl.jsolve.sweetener.exception;

public class FileNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public FileNotFoundException(String fileName) {
        super(String.format("%s does not exist", fileName));
    }
}

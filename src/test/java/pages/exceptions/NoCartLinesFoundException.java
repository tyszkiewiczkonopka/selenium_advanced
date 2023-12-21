package pages.exceptions;

public class NoCartLinesFoundException extends RuntimeException {
    public NoCartLinesFoundException() {
        super("No cart lines found in the shopping cart.");
    }
}

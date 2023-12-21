package pages.exceptions;

public class OrderReferenceNotFoundException extends RuntimeException {
    public OrderReferenceNotFoundException(String orderReference) {
        super("Order reference not found: " + orderReference);
    }
}

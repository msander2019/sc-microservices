package ua.kyiv.mesharea.bill.exception;

public class BillNotFoundException extends RuntimeException {

    public BillNotFoundException(String message) {
        super(message);
    }
}

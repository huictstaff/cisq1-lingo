package nl.hu.cisq1.lingo.domain.exception;

public class RoundDoesNotBelongToGameException extends RuntimeException {
    public RoundDoesNotBelongToGameException() {
        super("This Round does not belong to your Game!");
    }
}
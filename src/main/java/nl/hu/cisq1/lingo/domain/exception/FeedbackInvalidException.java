package nl.hu.cisq1.lingo.domain.exception;

public class FeedbackInvalidException extends RuntimeException {
    public FeedbackInvalidException() {
        super("The amount of marks is not the same as the length of the word!");
    }
}
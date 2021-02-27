package nl.hu.cisq1.lingo.domain.exception;

public class InvalidFeedbackException extends RuntimeException {
    public InvalidFeedbackException() {
        super("The amount of marks is not the same as the length of the word!");
    }
}
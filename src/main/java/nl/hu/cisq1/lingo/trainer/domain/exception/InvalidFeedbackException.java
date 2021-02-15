package nl.hu.cisq1.lingo.trainer.domain.exception;

public class InvalidFeedbackException extends RuntimeException {
    public InvalidFeedbackException(String invalid_input_length) {
        super(invalid_input_length);
    }

    public static InvalidFeedbackException incorrectLength() {
        return new InvalidFeedbackException(String.format("invalid input length"));
    }
}

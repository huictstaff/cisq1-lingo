package nl.hu.cisq1.lingo.domain.exception;

public class InvalidFeedbackException extends RuntimeException {
    public InvalidFeedbackException(Integer feedbackLength, Integer attemptLength) {
        super("The length of the feedback (" + feedbackLength + ") does'nt match the attempted word length (" + attemptLength + ")." );
    }
}

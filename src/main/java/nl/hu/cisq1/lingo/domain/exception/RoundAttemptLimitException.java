package nl.hu.cisq1.lingo.domain.exception;

public class RoundAttemptLimitException extends RuntimeException {
    public RoundAttemptLimitException() {
        super("The attempt limit is reached! You may not make another guess!");
    }
}
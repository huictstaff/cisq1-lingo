package nl.hu.cisq1.lingo.trainer.domain.exception;

public class InvalidFeedbackException extends RuntimeException {
    public InvalidFeedbackException(String guess, int requiredWordLength, int round) {
        super(String.format("Guess %s with a length of %d doens't match the required length of %d for round %d", guess, guess.length(), requiredWordLength, round));
    }
}

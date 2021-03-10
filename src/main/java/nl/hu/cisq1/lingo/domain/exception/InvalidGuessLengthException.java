package nl.hu.cisq1.lingo.domain.exception;

public class InvalidGuessLengthException extends RuntimeException {
    public InvalidGuessLengthException() {
        super("The length of your guess isn't as long as the word to guess." );
    }
}

package nl.hu.cisq1.lingo.trainer.domain.exception;

public class InvalidGuessLengthException extends RuntimeException {
    public InvalidGuessLengthException() {
        super("Length of guess is not matching the length of the word that has to be guessed.");
    }
}

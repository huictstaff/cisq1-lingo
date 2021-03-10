package nl.hu.cisq1.lingo.domain.exception;

public class forbiddenGuessException extends RuntimeException {
    public forbiddenGuessException(){
        super("The guess you've tried to do was forbidden.");
    }
}

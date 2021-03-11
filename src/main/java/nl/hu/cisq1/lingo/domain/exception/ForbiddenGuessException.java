package nl.hu.cisq1.lingo.domain.exception;

public class ForbiddenGuessException extends RuntimeException {
    public ForbiddenGuessException(){
        super("The guess you've tried to do was forbidden.");
    }
}

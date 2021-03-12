package nl.hu.cisq1.lingo.domain.exception;

public class ForbiddenRoundException extends RuntimeException {
    public ForbiddenRoundException(){
        super("The round you've tried to create was forbidden.");
    }
}

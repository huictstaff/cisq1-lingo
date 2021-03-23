package nl.hu.cisq1.lingo.domain.exception;

public class ForbiddenRoundException extends RuntimeException {
    public ForbiddenRoundException(String msg){
        super("The round you've tried to create was forbidden because:" + msg);
    }
}

package nl.hu.cisq1.lingo.trainer.domain.exeption;

public class InvalidAttemptException extends RuntimeException  {

    public InvalidAttemptException(String message) {
        super(message);
    }
}

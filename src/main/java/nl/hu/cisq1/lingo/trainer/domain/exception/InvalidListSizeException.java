package nl.hu.cisq1.lingo.trainer.domain.exception;

public class InvalidListSizeException extends RuntimeException {
    public InvalidListSizeException(){
        super("size of list is faulty");
    }
}

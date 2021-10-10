package nl.hu.cisq1.lingo.lingoTrainer.domain.exception;

public class WordLengthNotSupportedException extends RuntimeException {
    public WordLengthNotSupportedException(Integer length) {
        super("Could not find word of length " + length);
    }
}

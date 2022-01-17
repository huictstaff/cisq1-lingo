package nl.hu.cisq1.lingo.words.domain.exception;

public class WordNotFoundException extends RuntimeException{
    public WordNotFoundException(String word) {
        super("Could not find word " + word);
    }

}

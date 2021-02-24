package nl.hu.cisq1.lingo.domain.exception;

public class InvalidFeedbackException extends RuntimeException {
    public InvalidFeedbackException(String message) {
        super(message);
    }


    public static InvalidFeedbackException guessLengthMissmatch(int guessLength, int wordLength) {
        return new InvalidFeedbackException(
                String.format(
                        "Size of guess (%s) is different to that of word (%s)",
                        guessLength,
                        wordLength
                )
        );
    }
}

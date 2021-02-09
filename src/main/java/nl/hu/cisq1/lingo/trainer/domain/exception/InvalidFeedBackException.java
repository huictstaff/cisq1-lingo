package nl.hu.cisq1.lingo.trainer.domain.exception;

public class InvalidFeedBackException extends RuntimeException{
    public InvalidFeedBackException(String invalid_input_length){super(invalid_input_length);}

    public static InvalidFeedBackException incorrectLength (){
        return new InvalidFeedBackException(
                String.format("Invalid input length")
        );
    }
}

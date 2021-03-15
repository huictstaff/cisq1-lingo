package trainer.domain;

import java.util.List;
import java.util.stream.Collectors;

public class Round {
    private int guesses = 5;
    private final String word;
    private Boolean isWon = false;

    public Round(String word) {
        this.word = word;
    }

    public List<String> guessWord (String guess){
        if(guesses <= 0 || isWon){
            System.err.println("This round is over.");
            return null;
        }else{
            List<Mark> marks = Feedback.markAttempt(guess, word);
            Feedback feedback =  new Feedback(guess, marks);
            List<String> hint = feedback.giveHint(word, marks);
            if (word.equals(String.join("", hint))){
                isWon = true;
            }
            return hint;
        }
    }

    public Boolean getWon() {
        return isWon;
    }

    public int getGuesses() {
        return guesses;
    }
}

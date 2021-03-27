package trainer.domain;

import org.apache.tomcat.util.codec.binary.StringUtils;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.util.List;
import java.util.stream.Collectors;

@Entity(name = "rounds")
public class Round {
    private int guesses = 5;
    private String word;
    private Boolean isWon = false;
    @OneToOne
    private Feedback feedback;
    private String id;

    public Round(){

    }

    public Round(String word) {
        this.word = word;
        String str = "_";
        List<Mark> marks = Feedback.markAttempt(str.repeat(word.length()), word);
        this.feedback = new Feedback(str.repeat(word.length()), marks);
    }

    public Hint guessWord (String guess){
        if(guesses <= 0 || isWon){
            return null;
        }else{
            List<Mark> marks = Feedback.markAttempt(guess, word);
            Hint hint = feedback.giveHint(word, marks);
            if (word.equals(String.join("", hint.getHintStrings()))){
                isWon = true;
            }
            guesses--;
            return hint;
        }
    }

    public Boolean getWon() {
        return isWon;
    }

    public void setFeedback(Feedback feedback) {
        this.feedback = feedback;
    }

    public void setGuesses(int guesses) {
        this.guesses = guesses;
    }

    public void setWon(Boolean won) {
        isWon = won;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public Feedback getFeedback() {
        return feedback;
    }

    public String getWord() {
        return word;
    }

    public int getGuesses() {
        return guesses;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Id
    public String getId() {
        return id;
    }
}

package nl.hu.cisq1.lingo.trainer.domain;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import nl.hu.cisq1.lingo.trainer.Mark;
import nl.hu.cisq1.lingo.trainer.exception.InvalidFeedbackException;

import java.util.List;

@EqualsAndHashCode
@ToString
public class Feedback {
    private String guess;
    private List<Mark> markPerLetter;

    public Feedback(String guess, List<Mark> markPerLetter) {
        if(guess.length() != markPerLetter.size()) {
            throw new InvalidFeedbackException("Length of the guess is not the same as the amount of marks");
        }
        this.guess = guess;
        this.markPerLetter = markPerLetter;
    }

    //return true when all marks in markPerLetter are correct else false
    public Boolean isWordGuessed() {
        if(this.markPerLetter.contains(Mark.ABSENT) || this.markPerLetter.contains(Mark.PRESENT)) {
            return false;
        }
        return true;
    }

    public String giveHint() {
        StringBuilder hint = new StringBuilder("" + guess.charAt(0));
        for(int i = 1; i < this.guess.length(); i++) {
            if(this.markPerLetter.get(i) == Mark.CORRECT) {
                hint.append(this.guess.charAt(i));
            } else {
                hint.append(".");
            }
        }
        return hint.toString();
    }
}

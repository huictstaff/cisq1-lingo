package nl.hu.cisq1.lingo.trainer.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import nl.hu.cisq1.lingo.trainer.domain.enums.Mark;
import nl.hu.cisq1.lingo.trainer.exception.InvalidFeedbackException;

import java.io.Serializable;
import java.util.List;

@NoArgsConstructor
public class Feedback implements Serializable {
    @Getter
    private String guess;
    @Getter
    private List<Mark> markPerLetter;

    public Feedback(String guess, List<Mark> markPerLetter) {
        if(guess.length() != markPerLetter.size()) {
            throw new InvalidFeedbackException("⏺ ⏺ ⏺ ⏺ Length of the guess is not the same as the amount of marks ⏺ ⏺ ⏺ ⏺");
        }
        this.guess = guess;
        this.markPerLetter = markPerLetter;
    }

    //return true when all marks in markPerLetter are correct else false
    public Boolean isWordGuessed() {
        return !this.markPerLetter.contains(Mark.PRESENT) && !this.markPerLetter.contains(Mark.ABSENT);
    }

    //character is appended to hint if the corresponding index in markPerLetter is Mark.CORRECT
    //  or if the index already has a letter in the lastHint,
    //else(absent) "." is appended
    public String giveHint(String lastHint) {
        StringBuilder hint = new StringBuilder();
        for(int i = 0; i < this.guess.length(); i++) {
            if(this.markPerLetter.get(i).equals(Mark.CORRECT))
                hint.append(this.guess.charAt(i));
            else if(lastHint.charAt(i) != '.') {
                hint.append(lastHint.charAt(i));
            }
            else {
                hint.append('.');
            }
        }
        return hint.toString();
    }

    @JsonIgnore
    public static String createFirstHint(String word) {
        return word.charAt(0) +
                ".".repeat(word.length() - 1);
    }
}

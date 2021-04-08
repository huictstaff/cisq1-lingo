package nl.hu.cisq1.lingo.trainer.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

@Entity(name = "hints")
@NoArgsConstructor
@Getter
public class Hint {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String hintString;

    public Hint(String word, boolean firstHint) {
        if(!firstHint) {
            this.hintString = word;
            return;
        }

        StringBuilder firstHintString = new StringBuilder();

        firstHintString.append(word.charAt(0));

        for(int dot = 1; dot < word.length(); dot++) {
            firstHintString.append(".");
        }

        this.hintString = firstHintString.toString();
    }

    public Hint appendFeedback(Feedback feedback) {
        StringBuilder newHint = new StringBuilder();

        for (int i = 0; i < this.hintString.length(); i++) {
            if (this.hintString.charAt(i) != '.') {
                newHint.append(this.hintString.charAt(i));
            } else if (feedback.getMarks().get(i) == Mark.CORRECT) {
                newHint.append(feedback.getAttempt().charAt(i));
            } else {
                newHint.append('.');
            }
        }

        return new Hint(newHint.toString(), false);
    }
}

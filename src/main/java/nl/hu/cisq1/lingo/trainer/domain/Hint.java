package nl.hu.cisq1.lingo.trainer.domain;

import lombok.Getter;

import java.io.Serializable;

@Getter
public class Hint implements Serializable {
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

package nl.hu.cisq1.lingo.trainer.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import nl.hu.cisq1.lingo.trainer.domain.exception.InvalidFeedbackException;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
public class Feedback {
    private String word;
    private List<Mark> marks = new ArrayList<>();

    public Feedback(String word, List<Mark> marks) {
        if (marks.size() != word.length()) {
            throw new InvalidFeedbackException(String.valueOf(word.length()));
        }
        this.word = word;
        this.marks = marks;
    }

    public Feedback(String guess, String actualWord) {
        if (guess.length() != actualWord.length()) {
            throw new InvalidFeedbackException(String.valueOf(guess.length()));
        }
        this.word = guess;
        for (int index = 0; index < word.length(); index++) {
            char guessedLetter = word.charAt(index);
            char actualLetter = actualWord.charAt(index);
            if (guessedLetter == actualLetter) {
                this.marks.add(Mark.CORRECT);
            } else if (actualWord.indexOf(guessedLetter) != -1) {
                this.marks.add(Mark.PRESENT);
            } else this.marks.add(Mark.ABSENT);
        }
    }

    boolean isWordGuessed() {
        if (marks.isEmpty()) return false;
        return this.marks.stream().allMatch(mark -> mark == Mark.CORRECT);
    }
}

package nl.hu.cisq1.lingo.trainer.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter
@Setter
public class Hint {
    @Id
    @GeneratedValue
    private long id;
    private String guess;
    @ElementCollection
    private List<Mark> currentMarks;
    @ElementCollection
    private List<Character> previousHint;
    @ManyToOne
    @JoinColumn(name = "round_id")
    private Round round;

    public Hint(String guess, List<Mark> currentMarks, List<Character> previousHint) {
        this.guess = guess;
        this.currentMarks = currentMarks;
        this.previousHint = previousHint;
    }

    public Hint(Hint previousHint, Feedback currentFeedback) {
        this.currentMarks = currentFeedback.getMarks();
        this.guess = currentFeedback.getGuess();
        this.previousHint = previousHint.getHint();
    }

    public List<Character> getHint() {
        List<Character> hint = new ArrayList<>();
        if (isInvalid()) return hint;
        for (int i = 0; i < currentMarks.size(); i++) {
            Mark currentMark = currentMarks.get(i);
            char previousCharachter = this.previousHint.get(i);
            switch (currentMark) {
                case CORRECT:
                    hint.add(this.guess.charAt(i));
                    break;
                case ABSENT:
                default:
                    if (isNotHintCharacter(previousCharachter)) hint.add(previousCharachter);
                    else hint.add('.');
            }
        }
        return hint;
    }

    public boolean isInvalid() {
        return currentMarks == null || previousHint == null || guess == null || guess.length() < 5 || guess.length() > 7
                || guess.length() != currentMarks.size() || guess.length() != previousHint.size();
    }

    public static Hint initialHint(List<Mark> currentMarks, char firstCharacter, int length) {
        List<Character> previousHint = new ArrayList<>();
        previousHint.add(firstCharacter);
        StringBuilder woord = new StringBuilder();
        woord.append(firstCharacter);
        for (int i = 1; i < length; i++) {
            previousHint.add('.');
            woord.append('.');
        }
        return new Hint(woord.toString(), currentMarks, previousHint);
    }

    public static boolean isNotHintCharacter(char character) {
        return character != '.';
    }
}

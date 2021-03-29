package nl.hu.cisq1.lingo.trainer.domain;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
public class Hint {
    private String guess;
    private List<Mark> currentMarks;
    private List<Character> previousHint;

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

    private boolean isInvalid() {
        return currentMarks == null || previousHint == null || this.guess.length() < 5 || guess.length() > 7;
    }

    static Hint initialHint(List<Mark> currentMarks, char firstCharacter, int length) {
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

    private boolean isNotHintCharacter(char character) {
        return character != '.' && character != '*';
    }
}

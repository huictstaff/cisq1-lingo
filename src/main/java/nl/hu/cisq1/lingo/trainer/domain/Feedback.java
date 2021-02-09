package nl.hu.cisq1.lingo.trainer.domain;

import java.util.List;

import lombok.*;
import nl.hu.cisq1.lingo.trainer.domain.exception.InvalidFeedBackException;

@ToString @EqualsAndHashCode
public class Feedback {
    public final String attempt;
    private final List<Mark> mark;

    public Feedback(String attempt, List<Mark> mark) {
        this.attempt = attempt;
        this.mark = mark;
    }

    public boolean isWordGuessed() {
        for (Mark m : mark) if (m != Mark.CORRECT) return false;
        return true;
    }

    public boolean isGuessValid() {
        return attempt.length() >= 5 && attempt.length() <= 7;
    }

    @NonNull
    public List<Character> giveHint(List<Character> prevHint, String wordToGuess) {
        char[] at = attempt.toCharArray();
        char[] g = wordToGuess.toCharArray();

        if (!isWordGuessed() && isGuessValid()) {
            for (int i = 0; i < at.length; i++) {
                if (at[i] == g[i]) {
                    prevHint.add(i, at[i]);
                }
                else prevHint.add(i, '.');
            }
        }
        return prevHint;
    }
}

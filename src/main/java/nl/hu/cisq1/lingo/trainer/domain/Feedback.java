package nl.hu.cisq1.lingo.trainer.domain;

import nl.hu.cisq1.lingo.trainer.domain.exception.InvalidFeedbackException;

import java.util.ArrayList;
import java.util.List;

public class Feedback {
    private final String guess;
    private final List<Mark> marks;

    public Feedback(String guess, List<Mark> marks) {
        if (marks.size() != guess.length()) throw new InvalidFeedbackException();
        this.guess = guess;
        this.marks = marks;
    }

    public boolean wordIsGuessed() {
        if (!guessIsValid()) return false;
        return marks.stream().allMatch(mark -> mark == Mark.CORRECT);
    }

    public boolean guessIsValid() {
        return guess.length() >= 5 && guess.length() <= 7;
    }

    public List<Character> giveHint() {
        List<Character> hint = new ArrayList<>();
        if (guessIsValid()) {
            for (int i = 0; i < marks.size(); i++) {
                char letter = guess.charAt(i);
                Mark mark = marks.get(i);
                char hintLetter;
                if (mark == Mark.CORRECT) hintLetter = letter;
                else if (mark == Mark.PRESENT) {
                    hintLetter = '*';
                } else hintLetter = '.';
                hint.add(hintLetter);
            }
        }
        return hint;
    }
}

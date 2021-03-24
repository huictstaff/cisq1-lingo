package nl.hu.cisq1.lingo.trainer.domain;

import nl.hu.cisq1.lingo.trainer.domain.exception.InvalidListSizeException;

import java.util.ArrayList;
import java.util.List;

public class Hint {
    private List<Character> hint;

    public Hint(List<Character> hint) {
        this.hint = hint;
    }

    public void setHint(List<Character> hint) {
        this.hint = hint;
    }

    public List<Character> getHint() {
        return hint;
    }

    public List<Character> giveHint(List<Guess> guesses, List<Feedback> feedbackList, String word) {
        ArrayList<Character> charList = new ArrayList<>();
        if (feedbackList.size() != guesses.size()) {
            throw new InvalidListSizeException();
        }

        for (Guess guess : guesses) {
            for (int i = 0; i <= guess.getGuess().length(); i++) {
                List<Mark> marksList = feedbackList.get(i).getMarks();
                for (Mark mark : marksList) {
                    switch (mark) {
                        case CORRECT:
                            charList.set(i, word.charAt(i));
                        case PRESENT:
                            if (charList.get(i) != word.charAt(i)) {
                                charList.set(i, '+');
                            }
                        case ABSENT:
                            if (charList.get(i) != word.charAt(i)) {
                                charList.set(i, '-');
                            }
                    }
                }
            }
        }
        this.hint = charList;
        return charList;
    }
}

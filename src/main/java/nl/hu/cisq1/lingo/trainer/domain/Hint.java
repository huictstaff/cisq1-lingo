package nl.hu.cisq1.lingo.trainer.domain;

import nl.hu.cisq1.lingo.trainer.domain.exception.InvalidListSizeException;

import java.util.ArrayList;
import java.util.Collections;
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
        ArrayList<Character> charList = new ArrayList<>(Collections.nCopies(word.length(), null));
        if (feedbackList.size() != guesses.size()) {
            throw new InvalidListSizeException();
        }

        for (int i = 0; i < guesses.get(guesses.size() - 1).getGuess().length(); i++) {
            if (charList.get(i) == null) {
                for (int j = 0; j < feedbackList.get(feedbackList.size() - 1).getMarks().size(); j++) {
                    if (feedbackList.get(feedbackList.size() - 1).getMarks().get(j) == Mark.PRESENT) {
                        charList.set(j, '+');
                    } else if (feedbackList.get(feedbackList.size() - 1).getMarks().get(j) == Mark.ABSENT) {
                        charList.set(j, '-');
                    }
                }

            }
        }

        for (int i = 0; i < guesses.size(); i++) {
            List<Mark> marksList = feedbackList.get(i).getMarks();
            for (int j = 0; j < guesses.get(i).getGuess().length(); j++) {
                if (marksList.get(j).equals(Mark.CORRECT)) {
                    charList.set(j, word.charAt(j));
                }
            }
        }



        this.hint = charList;
        return charList;
    }
}

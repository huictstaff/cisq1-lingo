package nl.hu.cisq1.lingo.trainer.domain;

import nl.hu.cisq1.lingo.trainer.domain.exception.InvalidListSizeException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Hint {
    private List<Character> hintChars;

    public Hint(List<Character> hint) {
        this.hintChars = hint;
    }

    public void setHint(List<Character> hint) {
        this.hintChars = hint;
    }

    public List<Character> getHint() {
        return hintChars;
    }

    private ArrayList<Character> validateCorrects(ArrayList<Character> charList, List<Guess> guesses, String word, List<Feedback> feedbackList) {
        for (int i = 0; i < guesses.size(); i++) {
            List<Mark> marksList = feedbackList.get(i).getMarks();
            for (int j = 0; j < guesses.get(i).getWordattempt().length(); j++) {
                if (marksList.get(j).equals(Mark.CORRECT)) {
                    charList.set(j, word.charAt(j));
                }
            }
        }
        return charList;
    }

    public List<Character> giveHint(List<Guess> guesses, List<Feedback> feedbackList, String word) {
        ArrayList<Character> charList = new ArrayList<>(Collections.nCopies(word.length(), null));
        if (feedbackList.size() != guesses.size()) {
            throw new InvalidListSizeException();
        }

        for (int i = 0; i < guesses.get(guesses.size() - 1).getWordattempt().length(); i++) {
            if (charList.get(i) == null) {
                for (int j = 0; j < feedbackList.get(feedbackList.size() - 1).getMarks().size(); j++) {
                    if (feedbackList.get(feedbackList.size() - 1).getMarks().get(j) == Mark.PRESENT) {
                        charList.set(j, '+');
                        continue;
                    }
                    charList.set(j, '-');
                }
            }
        }

        validateCorrects(charList, guesses, word, feedbackList);

        this.hintChars = charList;
        return charList;
    }
}

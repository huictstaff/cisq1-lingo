package nl.hu.cisq1.lingo.trainer.domain;

import lombok.Data;
import nl.hu.cisq1.lingo.trainer.domain.exception.InvalidFeedbackException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;


@Data
public class Feedback {
    private final String attempt;
    private final List<Mark> marks;


    public static Feedback forCorrect(String attempt) {
        List<Mark> correctMarks = Collections.nCopies(attempt.length(), Mark.CORRECT);
        return new Feedback(attempt, correctMarks);
    }


    public static Feedback forInvalid(String attempt) {
        List<Mark> correctMarks = Collections.nCopies(attempt.length(), Mark.INVALID);
        return new Feedback(attempt, correctMarks);
    }

    public Feedback(String attempt, List<Mark> marks) {
        if (attempt.length() != marks.size())
            throw InvalidFeedbackException.guessLengthMissmatch(attempt.length(), marks.size());

        this.attempt = attempt;
        this.marks = marks;
    }

    public boolean isWordGuessed() {
        boolean containsIncorrect = marks.stream().allMatch(i -> i == Mark.CORRECT);
        if (containsIncorrect) {
            return true;
        }
        return false;
    }


    public boolean isGuessValid() {
        boolean containsInvalid = marks.stream().anyMatch(i -> i == Mark.INVALID);
        if (!containsInvalid) {
            return true;
        }
        return false;
    }

    public Hint giveHint(Hint earlierHint, String wordToGuess) {
        int index = earlierHint.getNextHintIndex();

        // convert wordToGet to a list of chars
        List<Character> WTGLetterList =  wordToGuess
                .chars()
                .mapToObj(e -> (char)e)
                .collect(Collectors.toList());

        // this gets the earlier hint's letterslist and converts it to an ArrayList
        List<Character> newHintLettersList = new ArrayList<>(earlierHint.getLettersList());

        // here is the placeholder of the new hinted letter replaced by the letter itself that
        // we get from the WTG, based on the index
        newHintLettersList.set(index, WTGLetterList.get(index));

        // return the new instantiated hint
        return new Hint(newHintLettersList);
    }





}

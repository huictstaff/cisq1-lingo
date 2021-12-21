package nl.hu.cisq1.lingo.trainer.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import nl.hu.cisq1.lingo.trainer.domain.enums.Mark;
import nl.hu.cisq1.lingo.trainer.domain.exception.InvalidFeedbackException;

import java.util.ArrayList;
import java.util.List;

@ToString
@Getter
@EqualsAndHashCode
public class Feedback {

    private final String answer;
    private final String attempt;

    private final List<Mark> marks;

    public Feedback(String answer, String attempt) {
        this.attempt = attempt;
        this.answer = answer;
        this.marks = makeGuess(answer, attempt);
    }

    private List<Mark> makeGuess(String answer, String attempt) {
        String[] answerLetters = answer.split("");
        String[] attemptLetters = attempt.split("");

        List<Mark> marks = new ArrayList<>();
        List<String> recheckLetters = new ArrayList<>();

        if (answerLetters.length != attemptLetters.length) {
            throw new InvalidFeedbackException();
        }

        // Check first for all correct values
        for (int i = 0; i < attemptLetters.length; i++) {
            if (attemptLetters[i].equals(answerLetters[i])) {
                marks.add(Mark.CORRECT);
            } else {
                marks.add(null);
                recheckLetters.add(answerLetters[i]);
            }
        }

        // Check for PRESENT and ABSENT
        for (int i = 0; i < attemptLetters.length; i++) {
            if (marks.get(i) == Mark.CORRECT) continue;
            if (recheckLetters.contains(attemptLetters[i])) {
                recheckLetters.remove(attemptLetters[i]);
                marks.set(i, Mark.PRESENT);
            } else {
                marks.set(i, Mark.ABSENT);
            }
        }

        return marks;
    }

    public boolean isWordGuessed() {
        return this.marks.stream().allMatch(Mark.CORRECT::equals);
    }

    public String giveHint(String previousHint) {
        StringBuilder word = new StringBuilder();
        String[] letters = this.attempt.split("");

        if (previousHint == null) {
            previousHint = letters[0] + ".".repeat(letters.length - 1);
        }

        String[] previousLetters = previousHint.split("");

        for (int i = 0; i < letters.length; i++) {
            if (this.marks.get(i).equals(Mark.CORRECT)) {
                word.append(letters[i]);
            } else {
                word.append(previousLetters[i]);
            }
        }

        return word.toString();
    }

}

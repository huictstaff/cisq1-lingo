package nl.hu.cisq1.lingo.trainer.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import nl.hu.cisq1.lingo.trainer.domain.exception.InvalidFeedbackException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@ToString
@Getter
public class Feedback {
    private final List<Mark> marks;
    private String attempt;
    private String wordToGuess;

    public Feedback(String wordToGuess, String attempt) {
        this.wordToGuess = wordToGuess;
        this.attempt = attempt;
        this.marks = makeGuess(attempt, wordToGuess);
    }

    private List<Mark> makeGuess(String attenpt, String wordToGuess) {
        String[] attemptLetters = attenpt.split("");
        String[] awnserLetters = wordToGuess.split("");
        List<Mark> markList = new ArrayList<>();

        if (awnserLetters.length != attemptLetters.length) {
//            markList.add(Mark.INVALID);
            isWordInvalid();
        }

        for (int i = 0; i < awnserLetters.length; i++) {
            if (awnserLetters[i].equals(attemptLetters[i])) {
                markList.add(Mark.CORRECT);
            } else if (awnserLetters[i].contains(attemptLetters[i])) {
                markList.add(Mark.PRESENT);
            } else {
                markList.add(Mark.ABSENT);
            }
        }
        return markList;
    }

    public boolean isWordGuessed() {
        return this.marks.stream()
                .allMatch(Mark.CORRECT::equals);
    }

    public void isWordInvalid() {
        throw new InvalidFeedbackException();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Feedback feedback = (Feedback) o;
        return Objects.equals(marks, feedback.marks);
    }

    @Override
    public int hashCode() {
        return Objects.hash(marks);
    }

    @Override
    public String toString() {
        return "Feedback{" +
                "marks=" + marks +
                '}';
    }

    public String getHint(String oldHint) {
        String woord = "";
        String[] letters = this.wordToGuess.split("");

        if (oldHint == null) {
            oldHint = letters[0] + ".".repeat(letters.length - 1);
        }

        String[] oldHintLetters = oldHint.split("");

        for (int i = 0; i < letters.length; i++) {
            if (this.marks.get(i).equals(Mark.CORRECT)) {
                woord += letters[i];
            } else {
                woord += oldHintLetters[i];
            }
        }
        return woord;
    }
}

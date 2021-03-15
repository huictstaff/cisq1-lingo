package trainer.domain;

import trainer.domain.exception.InvalidFeedbackException;

import java.lang.invoke.SwitchPoint;
import java.util.*;

public class Feedback {
    private List<Mark> marks;
    private String attempt;

    public Feedback(String attempt, List<Mark> marks) {
        this.marks = marks;
        this.attempt = attempt;
        if (marks.size() != attempt.length()) {
            throw new InvalidFeedbackException();
        }
    }

    public boolean isWordGuessed() {
        return marks.stream().allMatch(mark -> mark == Mark.CORRECT);
    }

    public boolean isGuessInvalid() {
        return marks.stream().anyMatch(mark -> mark == Mark.INVALID);
    }

    public static List<Mark> markAttempt(String attempt, String wordToGuess) {
        List<Mark> marks = new ArrayList<>();
        for (int i = 0; i < attempt.length(); i++) {
            if (attempt.charAt(i) == wordToGuess.charAt(i)) {
                marks.add(Mark.CORRECT);
            } else if (wordToGuess.contains(attempt.charAt(i) + "")) {
                marks.add(Mark.PRESENT);
            } else if (!Character.isLetter(attempt.charAt(i))) {
                marks.add(Mark.INVALID);
            } else {
                marks.add(Mark.INVALID);
            }
        }
        return marks;
    }

    public List<String> giveHint(String wordToGuess, List<Mark> marks) {
        List<String> hint = new ArrayList<>();
        for (int i = 0; i < wordToGuess.length(); i++) {
            switch (marks.get(i)) {
                case PRESENT:
                    hint.add("*");
                    break;

                case ABSENT:
                    hint.add("_");
                    break;

                case CORRECT:
                    hint.add("" + wordToGuess.charAt(i));
                    break;
                case INVALID:
                    hint.add("#");
                    break;

                default:
                    System.err.println("Error Invalid mark.");
                    break;
            }
        }
        return hint;
    }

    @Override
    public String toString() {
        return "Feedback{" +
                "marks=" + marks +
                ", attempt='" + attempt + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Feedback feedback = (Feedback) o;
        return Objects.equals(marks, feedback.marks) && Objects.equals(attempt, feedback.attempt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(marks, attempt);
    }
}

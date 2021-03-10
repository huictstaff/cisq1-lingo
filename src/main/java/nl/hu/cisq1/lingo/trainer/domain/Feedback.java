package nl.hu.cisq1.lingo.trainer.domain;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class Feedback implements Serializable {
    private final String attempt;
    private final List<Mark> marks;

    public Feedback(String attempt, List<Mark> marks) {
        this.attempt = attempt;
        this.marks = marks;
    }

    public boolean isWordGuessed() {
        return marks.stream().allMatch(mark -> mark == Mark.CORRECT);
    }

    public boolean isAttemptValid() {
        return marks.stream().noneMatch(mark -> mark == Mark.INVALID);
    }


    public String giveHint(String previousHint) {
        StringBuilder newHint = new StringBuilder();

        for(int i = 0; i < previousHint.length(); i++) {
            if(marks.get(i) == Mark.CORRECT) {
                newHint.append(attempt.charAt(i));
            } else if(previousHint.charAt(i) != '.') {
                newHint.append(previousHint.charAt(i));
            } else {
                newHint.append(".");
            }
        }

        return newHint.toString();
    }

    public String getAttempt() {
        return attempt;
    }

    public List<Mark> getMarks() {
        return marks;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Feedback feedback = (Feedback) o;
        return Objects.equals(attempt, feedback.attempt) && Objects.equals(marks, feedback.marks);
    }

    @Override
    public int hashCode() {
        return Objects.hash(attempt, marks);
    }
}

package trainer.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Feedback {
    private List<Mark> marks;
    private String attempt;

    public Feedback(List<Mark> marks, String attempt) {
        this.marks = marks;
        this.attempt = attempt;
    }

    public boolean isWordGuessed(){
        return marks.stream().allMatch(mark -> mark == Mark.CORRECT);
    }
    public boolean isGuessInvalid(){
        return marks.stream().anyMatch(mark -> mark == Mark.INVALID);
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

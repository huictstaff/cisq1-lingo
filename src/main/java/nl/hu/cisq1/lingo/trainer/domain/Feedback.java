package nl.hu.cisq1.lingo.trainer.domain;

import java.util.List;
import java.util.Objects;

public class Feedback {
    private List<Mark> marks;

    public Feedback(List<Mark> marks) {
        this.marks = marks;
    }

    public boolean isWordGuessed() {
        return this.marks.stream().allMatch(mark -> mark.equals(Mark.CORRECT));
    }

    public boolean isWordValid() {
        return this.marks.stream().noneMatch(mark -> mark.equals(Mark.INVALID));
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
}

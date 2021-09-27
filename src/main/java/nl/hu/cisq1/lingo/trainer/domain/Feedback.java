package nl.hu.cisq1.lingo.trainer.domain;

import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;

public class Feedback {
    private String attempt;
    private List<Mark> markList;

    public Feedback(String attempt, List<Mark> markList) {
        this.attempt = attempt;
        this.markList = markList;
    }

    public boolean isWordGuessed() {
        return markList.stream().allMatch(result -> result == Mark.CORRECT);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Feedback feedback = (Feedback) o;
        return Objects.equals(attempt, feedback.attempt) &&
                Objects.equals(markList, feedback.markList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(attempt, markList);
    }

    @Override
    public String toString() {
        return "Feedback{" +
                "attempt='" + attempt + '\'' +
                ", markList=" + markList +
                '}';
    }
}

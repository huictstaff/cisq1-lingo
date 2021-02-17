package nl.hu.cisq1.lingo.trainer.domain;

import nl.hu.cisq1.lingo.trainer.domain.exception.InvalidFeedbackException;

import java.util.List;
import java.util.Objects;

public class Feedback {
    private final List<Mark> marks;
    private String attempt;

    public Feedback(List<Mark> marks, String attempt) {
        if (!(marks.size() == attempt.length())){
            throw new InvalidFeedbackException();
        }else{
            this.marks = marks;
            this.attempt = attempt;
        }
    }

//    public Feedback(List<Mark> marks) {
//        this.marks = marks;
//    }

    public boolean isWordGuessed() {
        return this.marks.stream()
                .allMatch(Mark.CORRECT::equals);
    }

    public boolean isWordInvalid() {
        return this.marks.stream()
                .allMatch(Mark.INVALID::equals);
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
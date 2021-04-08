package nl.hu.cisq1.lingo.trainer.domain;

import lombok.Generated;
import lombok.Getter;

import java.io.Serializable;
import java.util.List;

@Getter
public class Feedback {
    private final String attempt;
    private final List<Mark> marks;

    public Feedback(String attempt, List<Mark> marks) {
        this.attempt = attempt;
        this.marks = marks;
    }

    public Mark totalMark() {
        for(Mark mark : marks) {
            if(mark != Mark.CORRECT) {
                if(mark == Mark.ILLEGAL) return Mark.ILLEGAL;
                return Mark.WRONG;
            }
        }
        return Mark.CORRECT;
    }

    @Override
    public String toString() {
        return "Feedback{" +
                "attempt='" + attempt + '\'' +
                ", marks=" + marks +
                '}';
    }

    @Generated
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Feedback feedback = (Feedback) o;

        if (!attempt.equals(feedback.attempt)) return false;
        return marks.equals(feedback.marks);
    }

    @Generated
    @Override
    public int hashCode() {
        int result = attempt.hashCode();
        result = 31 * result + marks.hashCode();
        return result;
    }
}

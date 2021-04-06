package nl.hu.cisq1.lingo.trainer.domain;

import lombok.Generated;
import lombok.Getter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Getter
public class Feedback implements Serializable {
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

    public Feedback appendCorrect(Feedback feedback) {
        List<Mark> allCorrect = new ArrayList<>(this.marks);
        StringBuilder returnWord = new StringBuilder();

        for (int i = 0; i < this.marks.size(); i++) {
            if (feedback.getMarks().get(i) == Mark.CORRECT) {
                allCorrect.set(i, Mark.CORRECT);
                returnWord.append(feedback.getAttempt().toLowerCase().charAt(i));
            } else if (this.getMarks().get(i) == Mark.CORRECT) {
                allCorrect.set(i, Mark.CORRECT);
                returnWord.append(this.getAttempt().toLowerCase().charAt(i));
            } else {
                allCorrect.set(i, Mark.WRONG);
                returnWord.append(".");
            }
        }

        return new Feedback(returnWord.toString(), allCorrect);
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

}

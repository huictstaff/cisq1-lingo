package nl.hu.cisq1.lingo.trainer.domain;

import java.util.List;
import java.util.Objects;

public class Feedback {
    private String attempt;
    private List<Mark> mark;

   // public Feedback(String attempt, List<Mark> marks){}
    public Feedback(String attempt, List<Mark> mark){
        this.attempt = attempt;
        this.mark = mark;
    }

    @Override
    public String toString() {
        return "Feedback{" +
                "attempt='" + attempt + '\'' +
                ", mark=" + mark +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Feedback feedback = (Feedback) o;
        return Objects.equals(attempt, feedback.attempt) &&
                Objects.equals(mark, feedback.mark);
    }

    @Override
    public int hashCode() {
        return Objects.hash(attempt, mark);
    }

    public boolean isWordGuessed(){
        for(Mark m: this.mark){
            if(m != m.Correct){
                return false;
            }
        }
        return true;
    }

    public boolean guessIsValid() {
        if(5 <= attempt.length() && attempt.length() <=7){
            return true;
        }
        return false;
    }
}

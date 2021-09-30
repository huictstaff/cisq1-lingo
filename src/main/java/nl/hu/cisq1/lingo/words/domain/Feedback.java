package nl.hu.cisq1.lingo.words.domain;


import java.util.List;
import java.util.Objects;

public class Feedback
{
    String attempt;
    List<Mark> mark;

    public Feedback(String attempt, List<Mark> markList)
    {
        this.attempt = attempt;
        this.mark = markList;
    }

    public boolean isWordGuessed()
    {
        return mark.stream().allMatch(m -> m == Mark.CORRECT);
    }

    public boolean guessIsValid()
    {
        if(attempt.length() == 5) {return true;}
        else return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Feedback feedback = (Feedback) o;
        return Objects.equals(attempt, feedback.attempt) && Objects.equals(mark, feedback.mark);
    }

    @Override
    public int hashCode() {
        return Objects.hash(attempt, mark);
    }

    @Override
    public String toString() {
        return "Feedback{" +
                "attempt='" + attempt + '\'' +
                ", mark=" + mark +
                '}';
    }
}

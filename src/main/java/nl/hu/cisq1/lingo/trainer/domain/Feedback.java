package nl.hu.cisq1.lingo.trainer.domain;

import nl.hu.cisq1.lingo.trainer.exception.InvalidAttemptException;
import nl.hu.cisq1.lingo.trainer.exception.InvalidFeedbackException;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Feedback implements Serializable {
    private String attempt;
    private List<Mark> marks;

    public Feedback( String attempt, List<Mark> marks){
        this.attempt = attempt;
        if (marks.size()!= attempt.length())
            throw new InvalidFeedbackException("ongeldig feedback");
        this.marks = marks;
    }
    public boolean isWordGuessed(){
       return marks.stream().allMatch(mark -> mark == Mark.CORRECT);
    }
    public boolean isWordVlid(){
        return marks.stream().allMatch(mark -> mark != Mark.INVALID);
    }
    public List<String> gaveHint(){
        if (!isWordVlid())throw new InvalidAttemptException("invalid attempt");
        char[] attemptCharacter = attempt.toCharArray();
        List<String> hit = new ArrayList<>();
        for (int i = 0; i< marks.size();i++){
            if (marks.get(i) == Mark.CORRECT)hit.add(String.valueOf(attemptCharacter[i]));
            else if (marks.get(i) == Mark.ABSENT)hit.add(".");
            else if (marks.get(i) == Mark.PRESENT)hit.add(String.format("(%s)", attemptCharacter[i]));
        }
        return hit;
    }

    @Override
    public boolean equals (Object o) {
        if (this == o) return true;
        if (!( o instanceof Feedback )) return false;
        Feedback feedback = (Feedback) o;
        return Objects.equals(attempt, feedback.attempt) &&
                Objects.equals(marks, feedback.marks);
    }

    @Override
    public int hashCode ( ) {
        return Objects.hash(attempt, marks);
    }
}

package nl.hu.cisq1.lingo.trainer.domain;

import nl.hu.cisq1.lingo.trainer.exception.InvalidAttemptException;
import nl.hu.cisq1.lingo.trainer.exception.InvalidFeedbackException;
import org.apache.tomcat.util.buf.StringUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Feedback implements Serializable {
    private String attempt;
    private List<Mark> marks;
    private List<String> hint;

    public Feedback( String attempt, List<Mark> marks){
        this.attempt = attempt;
        if (marks.size()!= attempt.length())
            throw new InvalidFeedbackException("ongeldig feedback");
        this.marks = marks;
        this.hint = new ArrayList<>();

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
        for (int i = 0; i< marks.size();i++){
            if (marks.get(i) == Mark.CORRECT)this.hint.add(String.valueOf(attemptCharacter[i]));
            else if (marks.get(i) == Mark.ABSENT)this.hint.add(".");
            else if (marks.get(i) == Mark.PRESENT)this.hint.add(String.format("(%s)", attemptCharacter[i]));
        }
        return hint;
    }

    public static List<Mark> feedbackGenerator(String attempt, String wordToGuess){
        List<Mark> marks = new ArrayList<>();
        if ( attempt.length() != wordToGuess.length()){
            for (int i = 0; i < attempt.length(); i++){
                marks.add(Mark.INVALID);
            }
        }else {
            for (int i = 0; i < wordToGuess.length(); i++) {
                if (attempt.charAt(i) == wordToGuess.charAt(i))
                    marks.add(Mark.CORRECT);
                else if (wordToGuess.contains(String.valueOf(attempt.charAt(i)))) {
                    marks.add(Mark.PRESENT);
                } else marks.add(Mark.ABSENT);
            }
        }
        return marks;
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
        return Objects.hash(attempt, marks, hint);
    }
//    @Override
//    public boolean equals (Object o) {
//        if (this == o) return true;
//        if (!( o instanceof Feedback )) return false;
//        Feedback feedback = (Feedback) o;
//        return Objects.equals(attempt, feedback.attempt) &&
//                Objects.equals(marks, feedback.marks);
//    }
//
//    @Override
//    public int hashCode ( ) {
//        return Objects.hash(attempt, marks);
//    }
}

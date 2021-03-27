package trainer.domain;

import trainer.domain.exception.InvalidFeedbackException;

import javax.lang.model.element.Name;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.lang.invoke.SwitchPoint;
import java.util.*;

@Entity(name = "feedback")
public class Feedback {
    @OneToMany(mappedBy = "marks")
    private List<Mark> marks;
    private String attempt;
    private String id;

    public Feedback(String attempt, List<Mark> marks) {
        this.marks = marks;
        this.attempt = attempt;
        if (marks.size() != attempt.length()) {
            throw new InvalidFeedbackException();
        }
    }

    public Feedback() {

    }

    public static List<Mark> markAttempt(String attempt, String wordToGuess) {
        List<Mark> marks = new ArrayList<>();
        for (int i = 0; i < attempt.length(); i++) {
            if (attempt.charAt(i) == wordToGuess.charAt(i)) {
                marks.add(Mark.CORRECT);
            } else if (wordToGuess.contains(attempt.charAt(i) + "")) {
                marks.add(Mark.PRESENT);
            } else if (!Character.isLetter(attempt.charAt(i))) {
                marks.add(Mark.INVALID);
            } else {
                marks.add(Mark.ABSENT);
            }
        }
        return marks;
    }

    public List<Mark> addMarks(List<Mark> oldMarks, List<Mark> currentMarks){
        List<Mark> newMarks = new ArrayList<>();
        for(int i = 0; i < oldMarks.size(); i++){
            if (oldMarks.get(i) == Mark.CORRECT){
                newMarks.add(Mark.CORRECT);
            }else{
                newMarks.add(currentMarks.get(i));
            }
        }
        return newMarks;
    }

    public Hint giveHint(String wordToGuess, List<Mark> hintMarks) {
        List<String> hintString = new ArrayList<>();
        List<Mark> newMarks = addMarks(marks, hintMarks);
        for (int i = 0; i < wordToGuess.length(); i++) {
            switch (newMarks.get(i)) {
                case PRESENT -> hintString.add("*");
                case ABSENT -> hintString.add("_");
                case CORRECT -> hintString.add("" + wordToGuess.charAt(i));
                case INVALID -> hintString.add("#");
                default -> System.err.println("Error Invalid mark.");
            }
        }
        this.marks = newMarks;
        return new Hint(hintString, newMarks);
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

    public void setId(String id) {
        this.id = id;
    }

    public List<Mark> getMarks() {
        return marks;
    }

    public void setMarks(List<Mark> marks) {
        this.marks = marks;
    }

    public String getAttempt() {
        return attempt;
    }

    @Id
    public String getId() {
        return id;
    }
}

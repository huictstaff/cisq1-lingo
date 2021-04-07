package nl.hu.cisq1.lingo.trainer.domain;

import nl.hu.cisq1.lingo.trainer.domain.exception.InvalidFeedbackException;

import javax.persistence.*;
import java.util.*;

@Entity
public class Feedback {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ElementCollection(fetch = FetchType.EAGER)
    private List<Mark> marks;
    private String attempt;
    @OneToOne(mappedBy = "feedback", targetEntity = Round.class)
    private Round round;

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

    public List<Mark> getMarks() {
        return marks;
    }


    public String getAttempt() {
        return attempt;
    }

    public void setAttempt(String attempt) {
        this.attempt = attempt;
    }
    public void setMarks(List<Mark> marks) {
        this.marks = marks;
    }
    public void setRound(Round round) {
        this.round = round;
    }

    public Round getRound() {
        return round;
    }


    public Long getId() {
        return id;
    }
}

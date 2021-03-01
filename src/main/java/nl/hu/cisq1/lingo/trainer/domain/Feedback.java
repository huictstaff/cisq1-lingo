package nl.hu.cisq1.lingo.trainer.domain;

import nl.hu.cisq1.lingo.trainer.domain.exception.InvalidFeedbackException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Feedback {
    private final List<Mark> marks;
    private String attempt;
    private String wordToGuess;

    public Feedback(String wordToGuess, String attempt) {
        this.wordToGuess = wordToGuess;
        this.attempt = attempt;
        this.marks = makeGuess(attempt,wordToGuess);
        if (this.marks.contains(Mark.INVALID)){
            throw new InvalidFeedbackException();
        }
    }

//    public Feedback(List<Mark> marks, String attempt) {
//        if (!(marks.size() == attempt.length())){
//            throw new InvalidFeedbackException();
//        }else{
//            this.marks = marks;
//            this.attempt = attempt;
//        }
//    }
    public List<Mark> makeGuess(String attenpt, String wordToGuess){
        String[] attemptLetters = attenpt.split("");
        String[] awnserLetters = wordToGuess.split("");
        List<Mark> markList = new ArrayList<>();
        if(awnserLetters.length != attemptLetters.length) {
            markList.add(Mark.INVALID);
        }else {
            for (int i = 0; i < awnserLetters.length; i++){
                if (awnserLetters[i].equals(attemptLetters[i])){
                    markList.add(Mark.CORRECT);
                }else if (awnserLetters[i].contains(attemptLetters[i])){
                    markList.add(Mark.PRESENT);
                }
                else{
                    markList.add(Mark.ABSENT);
                }
            }
        }

        return markList;
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

    public String getHint(String oldHint){
        String woord = "";
        String[] letters  = this.wordToGuess.split("");

        if (oldHint == null){
            woord += letters[0];
            for (int i = 1; i < letters.length; i++){
                if (this.marks.get(i).equals(Mark.CORRECT)){
                    woord += letters[i];
                }else{
                    woord += ".";
                }
            }
        }
        else{
            String[] oldHintLetters = oldHint.split("");
            woord += letters[0];
            for (int i = 1; i < letters.length; i++){
                if (!oldHintLetters[i].equals(".")){
                    woord += oldHintLetters[i];
                }
                else {
                    if (this.marks.get(i).equals(Mark.CORRECT)){
                        woord += letters[i];
                    }else {
                        woord += ".";
                    }
                }
            }

        }
        return woord;
    }
}

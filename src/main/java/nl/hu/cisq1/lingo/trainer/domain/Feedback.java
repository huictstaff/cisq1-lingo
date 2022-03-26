package nl.hu.cisq1.lingo.trainer.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Feedback {
    private String attempt;
    private List<Mark> marks;
    private List<String> hint = new ArrayList<>();

    public Feedback(String attempt, List<Mark> marks) {
        this.attempt = attempt;
        this.marks = marks;
    }

    public boolean wordIsGuessed() {
        /** no loops, thank you very much */
        return marks.stream().allMatch(match -> match.equals(Mark.CORRECT));
    }

    public boolean guessIsValid() {
        /**  */
        return marks.stream().noneMatch(match -> match.equals(Mark.INVALID));
    }

    public List<String> giveHint(List<String> previousHint, String word) {
        /** TODO cant this be better and prettier */
        for (int i = 0; i < word.length(); i++) {
            if ((marks.get(i) == Mark.CORRECT) || (!previousHint.get(i).equals("."))) {
                this.hint.add(word.substring(i, i+1));
            }
            else {
                this.hint.add(".");
            }
        }
        return hint;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Feedback feedback = (Feedback) o;
        return Objects.equals(attempt, feedback.attempt) && Objects.equals(marks, feedback.marks);
    }

    @Override
    public int hashCode() {
        return Objects.hash(attempt, marks);
    }

    @Override
    public String toString() {
        return "Feedback{" +
                "attempt='" + attempt + '\'' +
                ", marks=" + marks +
                '}';
    }
}

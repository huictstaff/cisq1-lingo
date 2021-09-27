package nl.hu.cisq1.lingo.trainer.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;

public class Feedback {
    private String attempt;
    private List<Mark> markList;
    private List<Character> hintList = new ArrayList<>();
    private List<Character> previousHintList = new ArrayList<>();


    public Feedback(String attempt, List<Mark> markList) {
        this.attempt = attempt;
        this.markList = markList;
        for (int index=0 ; index<attempt.length(); index++  ) {
            String empty = "-";
            char toAdd = empty.charAt(0);
            hintList.add(toAdd);
        }
    }

    public List<Character> giveHint() {
        int index = 0;
        for (Mark mark: markList){
            System.out.println(hintList);
            System.out.println(mark);
            System.out.println(attempt.charAt(index));
            System.out.println(index);

            if (mark==Mark.CORRECT){

                char toAdd = attempt.charAt(index);
                hintList.set(index,toAdd);
            }
            index+=1;
        }
        System.out.println(hintList.toString());
        return hintList;
    }

    public boolean isWordGuessed() {
        return markList.stream().allMatch(result -> result == Mark.CORRECT);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Feedback feedback = (Feedback) o;
        return Objects.equals(attempt, feedback.attempt) &&
                Objects.equals(markList, feedback.markList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(attempt, markList);
    }

    @Override
    public String toString() {
        return "Feedback{" +
                "attempt='" + attempt + '\'' +
                ", markList=" + markList +
                '}';
    }
}

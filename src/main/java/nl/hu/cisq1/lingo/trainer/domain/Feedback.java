package nl.hu.cisq1.lingo.trainer.domain;

import java.util.List;
import java.util.function.Predicate;

public class Feedback {
    private String attempt;
    private List<Mark> markList;

    public Feedback(String attempt, List<Mark> markList) {
        this.attempt = attempt;
        this.markList = markList;
    }

    public boolean isWordGuessed() {
        return markList.stream().allMatch(result -> result == Mark.CORRECT);
    }
}

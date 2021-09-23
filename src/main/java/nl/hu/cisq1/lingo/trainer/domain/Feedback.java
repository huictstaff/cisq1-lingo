package nl.hu.cisq1.lingo.trainer.domain;

import java.util.List;

public class Feedback {
    private String attempt;
    private List<Mark> mark;

    public Feedback(String attempt, List<Mark> mark) {
        this.attempt = attempt;
        this.mark = mark;
    }

    public boolean isWordGuessed() {
        return true;
    }
}

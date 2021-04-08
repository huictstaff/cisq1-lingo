package nl.hu.cisq1.lingo.trainer.domain;

import java.util.List;

public class Hint {
    private List<String> hintStrings;
    private List<Mark> marks;

    public Hint(List<String> hintStrings, List<Mark> marks) {
        this.marks = marks;
        this.hintStrings = hintStrings;
    }

    @Override
    public String toString() {
        return "Hint{" +
                "hintStrings=" + hintStrings +
                ", marks=" + marks +
                '}';
    }

    public List<String> getHintStrings() {
        return hintStrings;
    }

    public List<Mark> getMarks() {
        return marks;
    }
}

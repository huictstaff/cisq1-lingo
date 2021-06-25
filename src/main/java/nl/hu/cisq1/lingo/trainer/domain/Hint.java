package nl.hu.cisq1.lingo.trainer.domain;

import lombok.Getter;

import java.util.List;

@Getter
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
}

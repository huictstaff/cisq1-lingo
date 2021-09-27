package nl.hu.cisq1.lingo.trainer.domain;

import java.util.List;

public class Feedback {
    private String attempt;
    private List<Mark> markList;

    public Feedback(String attempt, List<Mark> markList) {
        this.attempt = attempt;
        this.markList = markList;
    }

    public boolean isWordGuessed() {
        for (Mark result : markList){
            if(result!=Mark.CORRECT){
                return false;
            }
        }
        return true;
    }
}

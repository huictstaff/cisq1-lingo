package nl.hu.cisq1.lingo.trainer.domain;

import java.util.ArrayList;
import java.util.List;

public class Feedback {
    private String attempt;
    private List<Mark> list;

    public boolean isWordGuessed() {
        int counter = 0;
        boolean reply = false;

        for ( Mark feedB : this.list){
            if (feedB == Mark.CORRECT) {
                counter = counter + 1;
            }
        }
        if (counter==5){
            reply = true;
        }
        return reply;
    }

    public boolean WordIsNotGuessed() {
        int counter = 0;
        boolean reply = false;

        for ( Mark feedB : this.list){
            if (feedB == Mark.CORRECT) {
                counter = counter + 1;
            }
        }
        if (counter==5){
            reply = true;
        }
        return reply;
    }

    public enum Mark {
        CORRECT,
        INCORRECT,
        ABSENT
    }

    public Feedback(String attempt, List<Mark> list) {
        this.list    = list;
        this.attempt = attempt;
    }
}

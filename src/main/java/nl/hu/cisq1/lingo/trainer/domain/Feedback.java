package nl.hu.cisq1.lingo.trainer.domain;

import java.io.Serializable;
import java.util.List;

public class Feedback implements Serializable {
    private String attempt;
    private List<Mark> marks;

    public Feedback( String attempt, List<Mark> marks){
        this.attempt = attempt;
        if (attempt.length()==marks.size())this.marks = marks;
    }
    boolean isWordGuessed(){
       return marks.stream().allMatch(mark -> mark == Mark.CORRECT);
    }
    boolean isWordVlid(){
        return marks.stream().allMatch(mark -> mark != Mark.INVALID);
    }






}

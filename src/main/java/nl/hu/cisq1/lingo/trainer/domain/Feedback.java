package nl.hu.cisq1.lingo.trainer.domain;

import java.util.List;
import lombok.*;

@ToString @EqualsAndHashCode
public class Feedback {
    private final String attempt;
    private final List<Mark> mark;

    public Feedback (String attempt, List<Mark> mark){
        this.attempt = attempt;
        this.mark = mark;
    }

    public boolean isWordGuessed() {
        for (Mark m : mark) if (m != Mark.CORRECT) return false; return true;
    }

    public boolean isGuessValid(){
        return attempt.length() >=5 && attempt.length() <= 7;
    }

}

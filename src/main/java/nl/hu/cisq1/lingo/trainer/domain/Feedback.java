package nl.hu.cisq1.lingo.trainer.domain;

import java.util.List;
import lombok.*;
import nl.hu.cisq1.lingo.words.application.WordService;

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
}

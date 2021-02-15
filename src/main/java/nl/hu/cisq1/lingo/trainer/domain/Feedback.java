package nl.hu.cisq1.lingo.trainer.domain;

import lombok.AllArgsConstructor;
import nl.hu.cisq1.lingo.trainer.Mark;

import java.util.List;

@AllArgsConstructor
public class Feedback {
    private String guess;
    private List<Mark> markPerLetter;

    //return true when all marks in markPerLetter are correct
    //else false
    public Boolean isWordGuessed() {
        return true;
    }
}

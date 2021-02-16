package nl.hu.cisq1.lingo.trainer.domain;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import nl.hu.cisq1.lingo.trainer.Mark;

import java.util.List;

@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class Feedback {
    private String guess;
    private List<Mark> markPerLetter;

    //return true when all marks in markPerLetter are correct
    //else false
    public Boolean isWordGuessed() {
        if(this.markPerLetter.contains(Mark.ABSENT) || this.markPerLetter.contains(Mark.PRESENT)) {
            return false;
        }
        return true;
    }
}

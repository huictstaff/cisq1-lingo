package nl.hu.cisq1.lingo.trainer.domain;

import lombok.Getter;
import nl.hu.cisq1.lingo.trainer.domain.enums.Mark;
import nl.hu.cisq1.lingo.trainer.exception.InvalidFeedbackException;

import java.util.List;

public class Feedback {
    @Getter
    private String guess;
    @Getter
    private List<Mark> markPerLetter;

    public Feedback(String guess, List<Mark> markPerLetter) {
        if(guess.length() != markPerLetter.size()) {
            throw new InvalidFeedbackException("Length of the guess is not the same as the amount of marks");
        }
        this.guess = guess;
        this.markPerLetter = markPerLetter;
    }

    //return true when all marks in markPerLetter are correct else false
    public Boolean isWordGuessed() {
        return !this.markPerLetter.contains(Mark.PRESENT) && !this.markPerLetter.contains(Mark.ABSENT);
    }

    //character is appended to hint if the corresponding index in markPerLetter is Mark.CORRECT,
    //if character is present "*" is appended
    //else(absent) "-" is appended
    //ToDo: use other hints(combine them) https://discord.com/channels/747833599952420895/804818719976587274/810932393300197436
    public String giveHint() {
        StringBuilder hint = new StringBuilder();
        for(int i = 0; i < this.guess.length(); i++) {
            switch (this.markPerLetter.get(i)) {
                case CORRECT -> hint.append(this.guess.charAt(i));
                case PRESENT -> hint.append("*");
                default -> hint.append("-");
            }
        }
        return hint.toString();
    }
}

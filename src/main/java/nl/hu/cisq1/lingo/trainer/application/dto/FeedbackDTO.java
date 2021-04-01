package nl.hu.cisq1.lingo.trainer.application.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import nl.hu.cisq1.lingo.trainer.domain.Feedback;
import nl.hu.cisq1.lingo.trainer.domain.Mark;

import java.util.List;

@Getter
@AllArgsConstructor
public class FeedbackDTO {
    public String guess;
    public List<Mark> marks;

    public FeedbackDTO(Feedback feedback) {
        this.guess = feedback.getGuess();
        this.marks = feedback.getMarks();
    }
}

package nl.hu.cisq1.lingo.trainer.presentation.DTO;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import nl.hu.cisq1.lingo.trainer.domain.Feedback;
import nl.hu.cisq1.lingo.trainer.domain.Mark;
import nl.hu.cisq1.lingo.trainer.domain.Round;

import java.util.List;

@Getter
@Setter
@ToString
public class FeedbackDTO {
    private List<Mark> marks;
    private String attempt;

    @JsonCreator
    public FeedbackDTO() {

    }

    public FeedbackDTO(Feedback feedback) {
        this.marks = feedback.getMarks();
        this.attempt = feedback.getAttempt();
    }
}

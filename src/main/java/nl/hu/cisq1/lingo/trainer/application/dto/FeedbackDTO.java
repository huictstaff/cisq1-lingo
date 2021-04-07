package nl.hu.cisq1.lingo.trainer.application.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import nl.hu.cisq1.lingo.trainer.domain.Feedback;
import nl.hu.cisq1.lingo.trainer.domain.Mark;

import java.util.List;

@Getter
@ToString
@AllArgsConstructor
public class FeedbackDTO {
    public List<Mark> marks;

    public FeedbackDTO(Feedback feedback) {
        this.marks = feedback.getMarks();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FeedbackDTO)) return false;
        FeedbackDTO that = (FeedbackDTO) o;
        for (int i = 0; i < this.marks.size(); i++) {
            if (this.marks.get(i) != that.marks.get(i)) {
                return false;
            }
        }
        return true;
    }
}

package nl.hu.cisq1.lingo.trainer.presentation.dto;

import lombok.AllArgsConstructor;
import nl.hu.cisq1.lingo.trainer.domain.Feedback;

import java.util.List;

@AllArgsConstructor
public class HintDTO {
    public int score;
    public int tries;
    public List<Feedback> allFeedback;
    public String hint;
}

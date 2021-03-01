package nl.hu.cisq1.lingo.trainer.presentation.dto;

import lombok.AllArgsConstructor;
import nl.hu.cisq1.lingo.trainer.domain.Feedback;

@AllArgsConstructor
public class HintDTO {
    public int tries;
    public Feedback feedback;
    public String hint;
}

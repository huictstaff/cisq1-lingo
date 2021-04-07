package nl.hu.cisq1.lingo.trainer.presentation.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import nl.hu.cisq1.lingo.trainer.domain.Feedback;
import nl.hu.cisq1.lingo.trainer.domain.Hint;

@Getter
@AllArgsConstructor
public class GameStatus {
    private Long id;
    private Feedback lastFeedback;
    private Hint currentHint;

    public GameStatus(Long id, Hint currentHint) {
        this.id = id;
        this.currentHint = currentHint;
    }
}

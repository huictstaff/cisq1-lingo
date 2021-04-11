package nl.hu.cisq1.lingo.trainer.presentation.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import nl.hu.cisq1.lingo.trainer.domain.Feedback;
import nl.hu.cisq1.lingo.trainer.domain.Hint;

@Getter
@Setter
@AllArgsConstructor
public class GameStatus {
    private Long id;
    private Feedback lastFeedback;
    private Hint currentHint;
    private boolean lost = false;

    public GameStatus(Long id, Hint currentHint) {
        this.id = id;
        this.currentHint = currentHint;
    }

    public GameStatus(Long id, Feedback lastFeedback, Hint currentHint) {
        this.id = id;
        this.lastFeedback = lastFeedback;
        this.currentHint = currentHint;
    }

    public GameStatus(Long id, Hint currentHint, boolean lost) {
        this.id = id;
        this.currentHint = currentHint;
        this.lost = lost;
    }

}

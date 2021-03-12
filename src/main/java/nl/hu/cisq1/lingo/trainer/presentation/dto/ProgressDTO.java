package nl.hu.cisq1.lingo.trainer.presentation.dto;

import nl.hu.cisq1.lingo.trainer.domain.Feedback;
import nl.hu.cisq1.lingo.trainer.domain.GameStatus;

import java.util.List;

public class ProgressDTO {
    public final GameStatus gameStatus;
    public final int score;
    public final int currentRound;
    public final List<Feedback> feedbacks;

    public ProgressDTO(GameStatus gameStatus, int score, int currentRound, List<Feedback> feedbacks) {
        this.gameStatus = gameStatus;
        this.score = score;
        this.currentRound = currentRound;
        this.feedbacks = feedbacks;
    }
}
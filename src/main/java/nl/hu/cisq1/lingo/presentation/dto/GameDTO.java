package nl.hu.cisq1.lingo.presentation.dto;

import lombok.Data;
import nl.hu.cisq1.lingo.domain.Game;
import nl.hu.cisq1.lingo.domain.Enums.GameState;

@Data
public class GameDTO {
    private long GameId;
    private int score;
    private GameState gameState;
    private int roundsSurvived;

    public GameDTO(Game game) {
        GameId = game.getId();
        this.score = game.getScore();
        this.gameState = game.getGameState();
        this.roundsSurvived = game.getRounds().size();
    }
}

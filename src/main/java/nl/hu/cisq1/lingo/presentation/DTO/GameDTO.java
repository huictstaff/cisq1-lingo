package nl.hu.cisq1.lingo.presentation.DTO;

import lombok.Data;
import nl.hu.cisq1.lingo.domain.Game;
import nl.hu.cisq1.lingo.domain.Enums.GameState;
import nl.hu.cisq1.lingo.domain.Round;

import java.util.List;

@Data
public class GameDTO {
    private long GameId;
    private int score;
    private GameState gameState;


    public GameDTO(Game game) {
        GameId = game.getId();
        this.score = game.getScore();
        this.gameState = game.getGameState();

    }
}

package nl.hu.cisq1.lingo.presentation.DTO;

import lombok.Data;
import nl.hu.cisq1.lingo.domain.Game;
import nl.hu.cisq1.lingo.domain.GameState;
import nl.hu.cisq1.lingo.domain.Round;

import java.util.List;

@Data
public class GameDTO {
    private long GameId;
    private List<Round> rounds;
    private int score;
    private GameState gameState;

    public GameDTO(Game game) {
        System.out.println("Creating Game");
        GameId = game.getId();
        this.rounds = game.getRounds();
        this.score = game.getScore();
        this.gameState = game.getGameState();
    }
}

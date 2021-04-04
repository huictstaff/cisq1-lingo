package nl.hu.cisq1.lingo.presentation.dto;

import nl.hu.cisq1.lingo.domain.Enums.Rating;
import nl.hu.cisq1.lingo.domain.Game;

import java.util.List;

public class ProgresDTO {
    public long gameId;
    public long roundId;
    public String guess;
    public List<Character> hint;
    public List<Rating> ratings;

    public ProgresDTO(Game game){
        this.gameId = game.getId();
        this.roundId = game.getLastRound().getId();
        this.guess = game.getLastRound().getFeedback().getAttempt();
        this.hint = game.getLastRound().getFeedback().getHint();
        this.ratings = game.getLastRound().getFeedback().getRatings();
    }
}

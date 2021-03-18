package nl.hu.cisq1.lingo.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import nl.hu.cisq1.lingo.domain.exception.ForbiddenRoundException;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
public class Game {
    @Id
    @GeneratedValue
    private long id;
    @OneToMany(mappedBy="game",fetch=FetchType.EAGER,cascade=CascadeType.ALL)
    private List<Round> rounds = new ArrayList<>();
    private GameState gameState;
    private int score;

    public Game(String firstWord) {
        Round ronde = new Round(firstWord);
        ronde.setGame(this);
        rounds.add(ronde);

        this.gameState = GameState.PLAYING;
    }

    public Round startNewRound(String word){
        if(gameState.equals(GameState.ELIMINATED))throw new ForbiddenRoundException();
        for(Round round : rounds){
            if (round.getRoundOver() != RoundStatus.WORD_IS_GUESSED) throw new ForbiddenRoundException();
        }
        Round newRound = new Round(word);
        rounds.add(newRound);
        return newRound;
    }

    public int calculateWordLength(){
        //per ronde loopt het op van 5 > 7
        return 4 + ((rounds.size()-1)%3+1);
    }

    public Round getLastRound(){
        return rounds.get(rounds.size()-1);
    }
}

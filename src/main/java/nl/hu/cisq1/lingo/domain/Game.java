package nl.hu.cisq1.lingo.domain;

import lombok.Data;
import nl.hu.cisq1.lingo.domain.Enums.GameState;
import nl.hu.cisq1.lingo.domain.Enums.RoundStatus;
import nl.hu.cisq1.lingo.domain.exception.ForbiddenRoundException;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Game {
    @Id
    @GeneratedValue
    private long id;
    @OneToMany(cascade=CascadeType.ALL)
    @JoinColumn(name="game_id")
    private List<Round> rounds = new ArrayList<>();
    @Enumerated(EnumType.STRING)
    private GameState gameState;
    private int score;

    public Game(){}
    public Game(String firstWord) {
        Round ronde = new Round(firstWord);
        rounds.add(ronde);
        this.gameState = GameState.PLAYING;
    }


    public Round startNewRound(String word){
        if(checkGamestate() == GameState.ELIMINATED)throw new ForbiddenRoundException();
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

    public GameState checkGamestate() {
        if(getLastRound().getRoundOver().equals(RoundStatus.ROUND_IS_RUNNING)) this.gameState = GameState.PLAYING;
        if(getLastRound().getRoundOver().equals(RoundStatus.ROUND_IS_FAILED)) this.gameState = GameState.ELIMINATED;
        if(getLastRound().getRoundOver().equals(RoundStatus.ROUND_IS_RUNNING)) this.gameState = GameState.PLAYING;
        return gameState;
    }
}

package nl.hu.cisq1.lingo.lingoTrainer.domain;

import javax.persistence.*;
import java.util.ArrayList;

enum GameStatus
{
    STARTED,
    STOPPED
}

@Entity
public class Game
{
    private ArrayList<Round> rounds = new ArrayList<>();

    @OneToOne
    private Round currentRound;

    @Id
    @GeneratedValue
    private Long Id;

    public Game(String wordToGuess)
    {
        this.currentRound = new Round(wordToGuess);
    }

    public ArrayList<Round> getRounds() {
        return rounds;
    }

    public void setRounds(ArrayList<Round> rounds) {
        this.rounds = rounds;
    }

    public Round getCurrentRound() {
        return currentRound;
    }

    public void setCurrentRound(Round currentRound) {
        this.currentRound = currentRound;
    }

    public void newRound(String wordToGuess)
    {
        if(currentRound != null && wordToGuess.length() > 4)
        {
            rounds.add(currentRound);
        }
        else{
            this.currentRound = new Round(wordToGuess);

        }
    }
}

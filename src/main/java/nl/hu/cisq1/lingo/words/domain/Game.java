package nl.hu.cisq1.lingo.words.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

enum GameStatus
{
    STARTED,
    STOPPED
}
public class Game
{
    private ArrayList<Round> rounds = new ArrayList<>();
    private Round currentRound;

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
        if(currentRound != null)
        {
            rounds.add(currentRound);
        }

        this.currentRound = new Round(wordToGuess);
    }
}

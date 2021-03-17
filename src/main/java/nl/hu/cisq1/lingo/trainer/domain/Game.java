package nl.hu.cisq1.lingo.trainer.domain;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class Game {
    private int id;
    private int score = 0;
    private List<Round> rounds = new ArrayList<>();
    private Round activeRound;

    public Game(int id, String initialWord) {
        this.id = id;
        Round round = new Round(initialWord);
        this.activeRound = round;
        this.rounds.add(round);
    }

    public Round newRound(String wordToGuess) {
        if (activeRound.isFinished()) {
            Round newRound = new Round(wordToGuess);
            this.rounds.add(newRound);
            this.activeRound = newRound;
        } return this.activeRound;
    }
}

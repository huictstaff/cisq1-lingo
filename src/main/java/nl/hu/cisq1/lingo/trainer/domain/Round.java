package nl.hu.cisq1.lingo.trainer.domain;

import lombok.Getter;
import nl.hu.cisq1.lingo.trainer.domain.enums.RoundType;

import java.util.ArrayList;

public class Round {
    private RoundType type;
    private String wordToGuess;
    @Getter private int tries;
    private ArrayList<Turn> allTurns = new ArrayList<>();

    public Round(RoundType type, String wordToGuess) {
        this.type = type;
        this.wordToGuess = wordToGuess;
        this.tries = 0;
    }
}

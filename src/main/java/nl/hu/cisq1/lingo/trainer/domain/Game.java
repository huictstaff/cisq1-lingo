package nl.hu.cisq1.lingo.trainer.domain;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private Progress progress = new Progress();
    private List<Round> rounds = new ArrayList<>();
    private GameStatus gameStatus = GameStatus.WAITING;
}
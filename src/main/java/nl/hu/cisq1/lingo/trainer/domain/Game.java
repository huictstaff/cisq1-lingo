package nl.hu.cisq1.lingo.trainer.domain;

import java.util.ArrayList;
import java.util.UUID;

public class Game {
    private final UUID gameId = UUID.randomUUID();
    private int score = 0;
    private ArrayList<Round> allRounds = new ArrayList<>();
}

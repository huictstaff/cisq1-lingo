package nl.hu.cisq1.lingo.games.domain;

import nl.hu.cisq1.lingo.games.data.Repository.WordRepository;

import java.util.List;

public class Game {
    private int id;
    private Round currentRound;
    private List<Round> playedRounds;

    private WordRepository wordRepository;



}

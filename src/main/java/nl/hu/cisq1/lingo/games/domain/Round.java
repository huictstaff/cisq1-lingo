package nl.hu.cisq1.lingo.games.domain;

import nl.hu.cisq1.lingo.games.data.Repository.WordRepository;

import java.time.Instant;

public class Round {
    private static final int MAX_ROUNDS = 5;

    private WordRepository wordRepository;

    private String word;
    private int attempts;
    private Instant lastAttempt;
    private boolean roundOver;

    public Round(WordRepository wordRepository, int length){
        this.wordRepository = wordRepository;
        this.word = this.wordRepository.randomWord(length);
        this.attempts = 0;
        this.roundOver = false;
    }
}

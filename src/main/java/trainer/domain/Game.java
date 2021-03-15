package trainer.domain;
import nl.hu.cisq1.lingo.words.application.WordService;
import nl.hu.cisq1.lingo.words.data.SpringWordRepository;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private List<Round> rounds = new ArrayList<>();

    public Game() {
    }

    public Round makeRound(String word){
        Round round = new Round(word);
        rounds.add(round);
        return round;
    }

    public List<Round> getRounds() {
        return rounds;
    }
}

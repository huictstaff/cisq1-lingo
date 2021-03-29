package nl.hu.cisq1.lingo.trainer.domain;


import lombok.*;
import nl.hu.cisq1.lingo.words.domain.Word;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Game {
    private Long id;
    private int score = 0;
    private List<Round> rounds = new ArrayList<>();

    public Game(Long id, Word initialWord) {
        this.id = id;
        Round round = new Round(initialWord);
        this.rounds.add(round);
    }

    public Round newRound(Word wordToGuess) {
        if (this.getActiveRound().isFinished()) {
            Round newRound = new Round(wordToGuess);
            this.rounds.add(newRound);
        } return this.getActiveRound();
    }

    public List<Character> guess(String guess) {
        if (this.getActiveRound().isFinished()) {
            return null;
        } else {
            return this.getActiveRound().guess(guess);
        }
    }

    public Round getActiveRound() {
        return this.rounds.get(this.rounds.size() - 1);
    }
}

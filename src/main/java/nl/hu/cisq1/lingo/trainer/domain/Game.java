package nl.hu.cisq1.lingo.trainer.domain;


import lombok.*;
import nl.hu.cisq1.lingo.words.domain.Word;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "game")
public class Game {
    @Id
    @GeneratedValue
    private Long id;
    private int score = 0;
    @OneToMany(mappedBy = "game", cascade = CascadeType.ALL)
    private List<Round> rounds = new ArrayList<>();

    public Game(Word initialWord) {
        Round round = new Round(initialWord);
        this.rounds.add(round);
        round.setGame(this);
    }

    public Round newRound(Word wordToGuess) {
        if (this.getActiveRound().isFinished()) {
            Round newRound = new Round(wordToGuess);
            this.rounds.add(newRound);

            newRound.setGame(this);
        } return this.getActiveRound();
    }

    public List<Character> guess(String guess) {
        if (this.getActiveRound().isFinished()) {
            return new ArrayList<>();
        } else {
            return this.getActiveRound().guess(guess);
        }
    }

    public Round getActiveRound() {
        return this.rounds.get(this.rounds.size() - 1);
    }
}

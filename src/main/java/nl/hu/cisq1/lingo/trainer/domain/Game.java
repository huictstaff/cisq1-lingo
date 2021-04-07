package nl.hu.cisq1.lingo.trainer.domain;


import lombok.*;
import nl.hu.cisq1.lingo.trainer.domain.exception.RoundIsFinishedException;

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
    @GeneratedValue(generator = "game_id_sequence")
    @SequenceGenerator(name="game_id_sequence", sequenceName = "game_id_seq")
    private Long id;
    private int score = 0;
    @OneToMany(mappedBy = "game", cascade = CascadeType.ALL)
    private List<Round> rounds = new ArrayList<>();

    public Game(String initialWord) {
        Round round = new Round(initialWord);
        this.rounds.add(round);
        round.setGame(this);
    }

    public Round newRound(String wordToGuess) {
        if (this.getActiveRound().isFinished()) {
            Round newRound = new Round(wordToGuess);
            this.rounds.add(newRound);

            newRound.setGame(this);
        } return this.getActiveRound();
    }

    public List<Character> guess(String guess) {
        if (this.getActiveRound().isFinished()) {
            throw new RoundIsFinishedException();
        } else {
            return this.getActiveRound().guess(guess);
        }
    }

    public int nextWordLength() {
        int prevLength = this.getActiveRound().getWordToGuess().length();
        switch (prevLength) {
            case 5:
                return 6;
            case 6:
                return 7;
            case 7:
            default:
                return 5;
        }
    }

    public Round getActiveRound() {
        return this.rounds.get(this.rounds.size() - 1);
    }
}

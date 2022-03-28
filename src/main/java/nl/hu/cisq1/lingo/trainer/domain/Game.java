package nl.hu.cisq1.lingo.trainer.domain;

import lombok.Getter;
import lombok.Setter;
import nl.hu.cisq1.lingo.trainer.domain.exeption.GameException;
import nl.hu.cisq1.lingo.words.domain.Word;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity(name = "game")
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Getter @Setter
    @Column
    private Gamestate gamestate;

    @Getter @Setter
    @Column
    private int score;

    /** nog een mappedBy = "game" toevoegen aan OneToMany? */
    @Getter @Setter
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private final List<Round> rounds = new ArrayList<>();

//    @Getter @Setter
//    private List<Word> previousWords = new ArrayList<>();

    public Game() {}

    public Game(Word word) {
        this.gamestate = gamestate.ACTIVE;
        this.rounds.add((new Round(word)));
    }

    public void guess(String attempt) {

        /** check if game is active */
        if (gamestate != Gamestate.ACTIVE) {
            throw new GameException("Currently not playing a game");
        }

        Round round = rounds.get(rounds.size() - 1);

        round.attempt(attempt);

        /** if word has been guessed put gamestate on waiting, calculate score */
        if (round.hasWordBeenGuessed) {
            gamestate = Gamestate.WAITING;
            score = score + (5 * (5 - round.getFeedback().size()) + 5);
        }

        /** if maximum amount of guesses has been used */
        if (round.getFeedback().size() >=5) {
            gamestate = Gamestate.FINISHED;
        }
    }


    public void startNewRound(Word word) {
        /** if to check if there is a game that is in gamestate waiting */
        if (gamestate.equals(Gamestate.WAITING)) {
            this.rounds.add(new Round(word));
            gamestate = Gamestate.ACTIVE;
        } else {
            /** throw at a wall when gamestate is not in WAITING */
            throw new GameException("Cant start a new round");
        }
    }

    /** At some point the word length must come into play */
    public int getWordLength() {
        return rounds.get(rounds.size() - 1).getWord().getLength();
    }
}

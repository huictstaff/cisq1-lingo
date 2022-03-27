package nl.hu.cisq1.lingo.trainer.domain;

import lombok.Getter;
import lombok.Setter;
import nl.hu.cisq1.lingo.trainer.domain.exeption.GameException;
import nl.hu.cisq1.lingo.words.domain.Word;

import java.util.ArrayList;
import java.util.List;

public class Game {

    @Getter @Setter
    private int id;
    @Getter @Setter
    private Gamestate gamestate;
    @Getter @Setter
    private int score;
    @Getter @Setter
    private final List<Round> rounds = new ArrayList<>();
    @Getter @Setter
    private List<Word> previousWords = new ArrayList<>();


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
            throw new GameException("Cant start a new round");
        }
    }

    /** TODO At some point the word length must come into play */
    public int getWordLength() {
        return 5;
    }
}

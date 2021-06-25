package nl.hu.cisq1.lingo.trainer.domain;

import lombok.Getter;
import nl.hu.cisq1.lingo.trainer.domain.exception.InvalidLengthException;
import nl.hu.cisq1.lingo.trainer.domain.exception.RoundIsOverException;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
@Entity(name = "games")
@Getter
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @OneToMany(mappedBy = "game", cascade = CascadeType.MERGE)
    private List<Round> rounds = new ArrayList<>();

    public Game() {
    }

    public Round makeRound(String word){
        Round round = new Round(word);
        rounds.add(round);
        return round;
    }

    @Override
    public String toString() {
        return "Game{" +
                "id=" + id +
                ", rounds=" + rounds +
                '}';
    }

    public Hint guessWord(String word){
        Round round = getLastRound();
        if (round.getGuesses() <= 0 || round.getWon()) {
            throw new RoundIsOverException();
        } else if(word.length() != round.getWord().length()){
            throw new InvalidLengthException();
        }
        else {
            List<Mark> marks = Feedback.markAttempt(word, round.getWord());
            Hint hint = round.getFeedback().giveHint(round.getWord(), marks);
            if (word.equals(String.join("", hint.getHintStrings()))) {
                round.setWon(true);
            }
            round.setGuesses(round.getGuesses() -1);
            return hint;
        }
    }


    public Round getLastRound(){
        return rounds.get(rounds.size()-1);
    }
}

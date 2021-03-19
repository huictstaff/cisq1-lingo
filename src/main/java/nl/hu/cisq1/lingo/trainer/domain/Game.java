package nl.hu.cisq1.lingo.trainer.domain;

import nl.hu.cisq1.lingo.trainer.exception.RoundPlayingException;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Game")
public class Game implements Serializable{
        @Id
        @GeneratedValue
        private Long id;

        private int score;

        @Lob
        private List<Round> rounds = new ArrayList<>();

        public Game(String word){
            Round round = new Round(word);
            this.rounds.add(round);
            this.score = 0;
        }
        public Game(){};

//        public void startGame(String string){
//            new Game(string).getLastRound().firstHint();
//        }
        public void startRound(String wordToGuess) {
                if (getLastRound().getRoundStatus() != RoundStatus.Win) {
                    throw new RoundPlayingException("Round stil playing");

            }else {
                Round round = new Round(wordToGuess);
                rounds.add(round);
            }
        }

        public void guessWord(String word){
            getLastRound().guessWord(word);
            if (getLastRound().isRoundWon())
                this.score +=scoreBerekening(getLastRound());

        }

        public Round getLastRound(){
            if (this.rounds.isEmpty()) return null;
            else return this.rounds.get(rounds.size()-1);
        }

        public int scoreBerekening(Round round){
           return  5 * (5 - round.getFeedbackList().size()) + 5;
        }

        public int wordLength(){
            if (getLastRound().getWordToGuess().length()< 7)
                return getLastRound().getWordToGuess().length()+1;
            else return 5;
        }

        public int getScore ( ) {
            return score;
        }
}

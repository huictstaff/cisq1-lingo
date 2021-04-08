package nl.hu.cisq1.lingo.trainer.domain;

import nl.hu.cisq1.lingo.trainer.domain.exception.InvalidLengthException;
import nl.hu.cisq1.lingo.trainer.domain.exception.RoundIsOverException;

import javax.persistence.*;
import java.util.List;

@Entity
public class Round {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    private Game game;
    private int guesses = 5;
    private String word;
    private Boolean isWon = false;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "feedback_id", referencedColumnName = "id")
    private Feedback feedback;

    public Round() {

    }

    public Round(String word, Game game ) {
        this.game = game;
        this.word = word;
        String str = "_";
        List<Mark> marks = Feedback.markAttempt(str.repeat(word.length()), word);
        this.feedback = new Feedback(str.repeat(word.length()), marks);
    }

    @Override
    public String toString() {
        return "Round{" +
                "id=" + id +
                ", guesses=" + guesses +
                ", word='" + word + '\'' +
                ", isWon=" + isWon +
                '}';
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public void setGuesses(int guesses) {
        this.guesses = guesses;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public void setWon(Boolean won) {
        isWon = won;
    }

    public void setFeedback(Feedback feedback) {
        this.feedback = feedback;
    }

    public Long getId() {
        return id;
    }

    public Game getGame() {
        return game;
    }

    public int getGuesses() {
        return guesses;
    }

    public String getWord() {
        return word;
    }

    public Boolean getWon() {
        return isWon;
    }

    public Feedback getFeedback() {
        return feedback;
    }
}

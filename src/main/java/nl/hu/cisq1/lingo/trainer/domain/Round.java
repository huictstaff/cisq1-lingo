package nl.hu.cisq1.lingo.trainer.domain;

import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
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

    public Round(String word) {
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

    public Boolean getWon() {
        return isWon;
    }
    public void setWon(Boolean isWon){
        this.isWon = isWon;
    }
    public void setGuesses(int guesses) {
        this.guesses = guesses;
    }
}

package nl.hu.cisq1.lingo.trainer.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Round
{
    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn
    private List<Feedback> feedbacks = new ArrayList<>();

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn
    private Feedback lastFeedback;

    private String previousHint;
    private String wordToGuess;
    private int timesGuessed;

    public Round(String wordToGuess)
    {
        this.wordToGuess = wordToGuess;
    }
    public Round(){}

    public Feedback GetGuessResult(String guessWord)
    {
        // create a new feedback
        var feedback = new Feedback(this.wordToGuess, guessWord);

        // calculate the marks
        feedback.calculateMarks();

        // increase times guessed by 1
        timesGuessed += 1;

        return feedback;
    }

    public int getTimesGuessed() {
        return timesGuessed;
    }
}

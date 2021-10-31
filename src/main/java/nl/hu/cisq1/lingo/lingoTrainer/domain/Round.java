package nl.hu.cisq1.lingo.lingoTrainer.domain;

import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Round implements Serializable
{
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    public String previousHint;
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    public String wordToGuess;
    @Cascade(org.hibernate.annotations.CascadeType.ALL)

    public int timesGuessed;

    @OneToOne
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    public Feedback previousFeedback;

    @javax.persistence.Id
    @GeneratedValue
    private Long Id;

    public String getPreviousHint() {
        return previousHint;
    }

    public void setPreviousHint(String previousHint) {
        this.previousHint = previousHint;
    }

    public String getWordToGuess() {
        return wordToGuess;
    }

    public void setWordToGuess(String wordToGuess) {
        this.wordToGuess = wordToGuess;
    }

    public Round(String wordToGuess) {
        previousHint = wordToGuess.charAt(0) + ",.".repeat(wordToGuess.length() - 1);
        this.wordToGuess = wordToGuess;
        this.timesGuessed = 0;
    }

    public Round() {
    }

    public Feedback guessWord(String attempt)
    {
        var feedback = new Feedback();
        feedback.giveHint(previousHint, wordToGuess, attempt);

        this.timesGuessed = this.timesGuessed + 1;
        previousFeedback = feedback;
        return feedback;
    }
}

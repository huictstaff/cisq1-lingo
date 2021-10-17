package nl.hu.cisq1.lingo.lingoTrainer.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Round
{
    public String previousHint;
    public String wordToGuess;
    public int timesGuessed;

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

    public Feedback guessWord(String attempt)
    {
        var feedback = new Feedback();
        feedback.giveHint(previousHint, wordToGuess, attempt);

        this.timesGuessed = this.timesGuessed + 1;

        return feedback;
    }
}

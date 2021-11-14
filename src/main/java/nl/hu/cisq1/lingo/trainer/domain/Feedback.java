package nl.hu.cisq1.lingo.trainer.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.util.ArrayList;

@Entity
public class Feedback
{
    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long Id;

    private String wordToGuess;
    private String guessWord;

    public String getWordToGuess() {
        return wordToGuess;
    }

    public String getGuessWord() {
        return guessWord;
    }

    public ArrayList<Mark> getMarks() {
        return marks;
    }

    ArrayList<Mark> marks = new ArrayList<>();


    public Feedback(String wordToGuess, String guessWord)
    {
    this.wordToGuess = wordToGuess;
    this.guessWord = guessWord;
    }

    public boolean wordIsGuessed()

    {
        return this.marks.stream().allMatch(m -> m == Mark.CORRECT);
    }

    public ArrayList<Mark> calculateMarks()
    {
        // empty marks list
        this.marks = new ArrayList<>();

        // checking each character of the word to guess and comparing it to the guess word.
        for(int i = 0; i < wordToGuess.length(); i++)
        {
            // the word to guess letter is at the same place as the to guess word letter,
            // so he guessed that letter correctly at that index.
            if(this.wordToGuess.charAt(i) == this.guessWord.charAt(i))
            {
            this.marks.add(Mark.CORRECT);
            }
            // the word to guess letter isn't at the same place as the to guess word letter.
            else
            {
                marks.add(Mark.ABSENT);
            }
        }

        return marks;
    }

}

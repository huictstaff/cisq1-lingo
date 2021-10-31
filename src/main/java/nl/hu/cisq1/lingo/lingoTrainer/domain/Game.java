package nl.hu.cisq1.lingo.lingoTrainer.domain;

import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;

@Entity
public class Game implements Serializable
{
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private ArrayList<Round> rounds = new ArrayList<>();

    @OneToOne
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private Round currentRound;

    @Id
    @GeneratedValue
    public Long Id;

    public Game(String wordToGuess)
    {
        this.currentRound = new Round(wordToGuess);
    }
    public Game()
    {
    }

    public ArrayList<Round> getRounds() {
        return rounds;
    }

    public void setRounds(ArrayList<Round> rounds) {
        this.rounds = rounds;
    }

    public Round getCurrentRound() {
        return currentRound;
    }

    public void setCurrentRound(Round currentRound) {
        this.currentRound = currentRound;
    }

    public String showStatus()
    {
       if(this.currentRound == null){
           return "Game id: " + this.Id + " has " + this.rounds.size() + " rounds.";
       }
       // TODO
       return "";
    }

    public void newRound(String wordToGuess)
    {
        if(currentRound != null && wordToGuess.length() > 4)
        {
            rounds.add(currentRound);
            this.currentRound = new Round(wordToGuess);

        }
        else{
            System.out.println("Error");
        }

    }
}

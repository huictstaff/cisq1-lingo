package nl.hu.cisq1.lingo.trainer.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Game
{
    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long Id;

    @ManyToMany (cascade = CascadeType.ALL)
    @JoinColumn
    private List<Round> rounds = new ArrayList<>();

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn
    private Round currentRound;

    public void saveCurrentRound()
    {
        this.rounds.add(currentRound);
    }

    public void setCurrentRound(Round currentRound) {
        this.currentRound = currentRound;
    }

    public Round getCurrentRound() {
        return currentRound;
    }
}

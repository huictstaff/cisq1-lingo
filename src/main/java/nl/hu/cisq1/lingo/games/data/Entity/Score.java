package nl.hu.cisq1.lingo.games.data.Entity;

import lombok.Getter;
import lombok.Setter;
import nl.hu.cisq1.lingo.games.domain.Player;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name="scores")
public class Score {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name="name", nullable = false)
    private String name;

    @Column(name="score", nullable = false)
    private int score;

    public Score(){}

    public Score(String name, int score){
        this.name = name;
        this.score = score;
    }
    public Score(long id, String name, int score){
        this.id = id;
        this.name = name;
        this.score = score;
    }
    public Score(Player player) {
        this(player.getPlayer(), player.getPoints());
    }

    // LOMBOK GETTERS & SETTERS \\
}

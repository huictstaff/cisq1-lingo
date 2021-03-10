package nl.hu.cisq1.lingo.trainer.data;

import nl.hu.cisq1.lingo.trainer.domain.Game;

import javax.persistence.*;

@Entity
@Table(name = "games")
public class GameEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Lob
    @Column(nullable = true)
    private Game game;

    public GameEntity(Game game) {
        this.game = game;
    }

    public GameEntity() {

    }

    public Long getId() {
        return id;
    }

    public Game getGame() {
        return game;
    }
}
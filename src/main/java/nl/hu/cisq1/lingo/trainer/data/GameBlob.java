package nl.hu.cisq1.lingo.trainer.data;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import nl.hu.cisq1.lingo.trainer.domain.Game;

import javax.persistence.*;

@Entity(name = "games")
@NoArgsConstructor
@Getter
public class GameBlob {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;

    @Lob
    @Column(name = "game_blob")
    private Game game;

    public GameBlob(Game game) {
        this.game = game;
    }
}

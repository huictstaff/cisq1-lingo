package nl.hu.cisq1.lingo.trainer.data;

import lombok.*;
import nl.hu.cisq1.lingo.trainer.domain.Game;

import javax.persistence.*;

@Entity(name = "games")
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
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

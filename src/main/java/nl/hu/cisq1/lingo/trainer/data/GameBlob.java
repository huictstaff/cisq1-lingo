package nl.hu.cisq1.lingo.trainer.data;

import lombok.Data;
import nl.hu.cisq1.lingo.trainer.domain.Game;

import javax.persistence.*;

@Entity(name = "games")
@Data
public class GameBlob {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private float Id;

    @Lob
    @Column(name = "game_blob")
    private Game game;

}

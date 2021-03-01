package nl.hu.cisq1.lingo.trainer.data;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import nl.hu.cisq1.lingo.trainer.domain.LingoGame;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "lingo")
@NoArgsConstructor
public class Game {
    @Id
    @GeneratedValue
    private Long id;

    @Lob
    @Getter
    private LingoGame lingoGame;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column
    private Date creationDate;

    @Column
    @Setter
    private Boolean gameDone;

    public Game(LingoGame lingoGame, Boolean gameDone) {
        this.lingoGame = lingoGame;
        this.gameDone = gameDone;
    }
}

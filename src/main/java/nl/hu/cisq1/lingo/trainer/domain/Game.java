package nl.hu.cisq1.lingo.trainer.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "Game")
public class Game implements Serializable{
        @Id
        private String id;

        private Long score;

        @Lob
        private List<Round> rounds;

}

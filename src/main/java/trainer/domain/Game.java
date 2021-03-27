package trainer.domain;
import nl.hu.cisq1.lingo.words.application.WordService;
import nl.hu.cisq1.lingo.words.data.SpringWordRepository;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
@Entity(name = "games")
public class Game {
    private Long id;
    @OneToMany(mappedBy = "rounds")
    private List<Round> rounds = new ArrayList<>();

    public Game() {
    }

    public Round makeRound(String word){
        Round round = new Round(word);
        rounds.add(round);
        return round;
    }

    public List<Round> getRounds() {
        return rounds;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Id
    public Long getId() {
        return id;
    }
}

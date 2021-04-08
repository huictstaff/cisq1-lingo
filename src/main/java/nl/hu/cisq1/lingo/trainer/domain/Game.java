package nl.hu.cisq1.lingo.trainer.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name = "games")
@NoArgsConstructor
@Getter
public class Game {

    @Id
    @Setter
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    private Round currentRound;

    private boolean going;

    public Game(Long id, String word) {
        this(word);
        this.id = id;
    }

    public Game(String word) {
        this.currentRound = new Round(word);
        this.going = true;
    }

    public Feedback guess(String guess) {
        Feedback feedback = currentRound.guess(guess);
        switch(feedback.totalMark()) {
            case ILLEGAL: {
                stopGame();
                break;
            }
            case WRONG: {
                if (currentRound.getTried() == 5) {
                    stopGame();
                }
                break;
            }
        }
        return feedback;
    }

    private void stopGame() {
        this.going = false;
    }

    public void startNewRound(String word) {
        if (!going) return;
        this.currentRound = new Round(word);
    }
}

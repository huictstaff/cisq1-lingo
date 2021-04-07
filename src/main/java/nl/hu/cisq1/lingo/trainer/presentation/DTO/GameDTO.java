package nl.hu.cisq1.lingo.trainer.presentation.DTO;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import nl.hu.cisq1.lingo.trainer.domain.Game;
import nl.hu.cisq1.lingo.trainer.domain.Round;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
public class GameDTO {
    private Long id;
    private List<RoundDTO> rounds = new ArrayList<>();

    @JsonCreator
    public GameDTO() {

    }

    public GameDTO(Game game) {
        List<RoundDTO> rounds = new ArrayList<>();
        game.getRounds().forEach(round -> {
            rounds.add(new RoundDTO(round));
        });
        this.id = game.getId();
        this.rounds = rounds;
    }
}

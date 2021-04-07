package nl.hu.cisq1.lingo.trainer.application.dto;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import nl.hu.cisq1.lingo.trainer.domain.Game;

import java.util.ArrayList;
import java.util.List;

@Getter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
public class GameDTO {
    public Long id;
    public List<RoundDTO> rounds = new ArrayList<>();

    public GameDTO(Game game) {
        this.id = game.getId();
        game.getRounds().forEach(round -> this.rounds.add(new RoundDTO(round)));
    }
}

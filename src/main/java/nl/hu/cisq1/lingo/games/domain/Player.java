package nl.hu.cisq1.lingo.games.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Player {

    private String player;
    private int points;

    public Player(String player, int points) {
        this.player = player;
        this.points = points;
    }
}

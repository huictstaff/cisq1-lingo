package nl.hu.cisq1.lingo.domain;

import lombok.EqualsAndHashCode;
import lombok.ToString;

@EqualsAndHashCode
@ToString
public class Game {
    private Round round;

    public Game(Round round) {
        this.round = round;
    }
}

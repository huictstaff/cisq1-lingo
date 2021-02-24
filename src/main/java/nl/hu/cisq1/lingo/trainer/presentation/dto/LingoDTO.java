package nl.hu.cisq1.lingo.trainer.presentation.dto;

import nl.hu.cisq1.lingo.trainer.domain.Game;

public class LingoDTO {
    public Game game;
    public LingoDTO(Game game) {
        this.game = game;
    }
}

package nl.hu.cisq1.lingo.trainer.presentation.dto;

import nl.hu.cisq1.lingo.trainer.domain.LingoGame;

public class LingoDTO {
    public LingoGame lingoGame;
    public LingoDTO(LingoGame lingoGame) {
        this.lingoGame = lingoGame;
    }
}

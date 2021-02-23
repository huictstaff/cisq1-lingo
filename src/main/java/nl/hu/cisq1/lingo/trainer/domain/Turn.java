package nl.hu.cisq1.lingo.trainer.domain;

import lombok.Getter;

public class Turn {
    @Getter private String guess;
    private Feedback feedback;
}

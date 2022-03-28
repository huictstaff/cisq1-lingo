package nl.hu.cisq1.lingo.trainer.domain;

import lombok.Getter;

import java.util.List;

public class Progress {

    @Getter
    private int id;

    @Getter
    private int score;

    @Getter
    private Gamestate gamestate;

    @Getter
    private List<Feedback> feedback;

    @Getter
    private int roundNumber;

    @Getter
    private List<Mark> marks;


    public Progress(int id, int score, Gamestate gamestate, List<Feedback> feedback, int roundNumber, List<Mark> marks) {
        this.id = id;
        this.score = score;
        this.gamestate = gamestate;
        this.feedback = feedback;
        this.roundNumber = roundNumber;
        this.marks = marks;
    }
}

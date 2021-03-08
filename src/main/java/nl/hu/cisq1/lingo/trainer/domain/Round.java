package nl.hu.cisq1.lingo.trainer.domain;

import org.springframework.hateoas.Link;

import java.io.Serializable;
import java.util.List;

public class Round implements Serializable {
    private int roundNumbers;
    private String wordToGuess;
    private List<String> attempts;
    private List<Feedback> feedbackList;

}

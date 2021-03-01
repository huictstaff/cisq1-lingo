package nl.hu.cisq1.lingo.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import nl.hu.cisq1.lingo.domain.exception.RoundAttemptLimitException;

import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode
@ToString
@Getter
public class Round {
    private final int number;
    private final Word word;
    private int attempt;
    private List<Feedback> feedbacks;

    public Round(int number, Word word, List<Feedback> feedbacks) {
        this.number = number;
        this.word = word;
        this.attempt = 0;
        this.feedbacks = feedbacks;
    }

    public List<Character> startRound() {
        List<Character> characters = new ArrayList<>();

        for (int i=0; i< this.word.getLength(); i++) {
            char letter = word.getValue().charAt(0);

            if(i == 0) {
                characters.add(letter);
            } else {
                characters.add('.');
            }
        }

        return characters;
    }

    public Feedback guessWord(String attempt) {

        if(this.attempt >=5) {
            throw new RoundAttemptLimitException();
        }

        List<Mark> marks = new ArrayList<>();

        for (int i=0; i< this.word.getLength(); i++) {

            if (attempt.charAt(i) == word.getValue().charAt(i)) {
                marks.add(Mark.CORRECT);
            } else if (word.getValue().contains(attempt.charAt(i) + "")) {
              marks.add(Mark.PRESENT);
            } else {
                marks.add(Mark.ABSENT);
            }
        }

        this.attempt++;

        return new Feedback(attempt, marks);
    }
}
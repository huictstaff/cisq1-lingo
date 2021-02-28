package nl.hu.cisq1.lingo.domain;

import nl.hu.cisq1.lingo.words.domain.Word;

import java.util.ArrayList;
import java.util.List;

public class Round {
    private int number;
    private Word word;
    private List<Feedback> feedbacks;

    public Round(int number, Word word, List<Feedback> feedbacks) {
        this.number = number;
        this.word = word;
        this.feedbacks = feedbacks;
    }

    public List<Character> startRound() {
        this.number ++;

        List<Character> characters = new ArrayList<>();

        for (int i=0; i< this.word.getLength(); i++) {
            char letter = word.getValue().charAt(0);

            if(i == 0) {
                characters.add(letter);
            } else {
                characters.add('_');
            }
        }

        return characters;
    }


    public Feedback guessWord(String attempt) {
        this.number ++;

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

        Feedback feedback = new Feedback(attempt, marks);

        return feedback;
    }
}
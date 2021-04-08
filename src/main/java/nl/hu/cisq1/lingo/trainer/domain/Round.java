package nl.hu.cisq1.lingo.trainer.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "rounds")
@NoArgsConstructor
@Getter
public class Round {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String wordToGuess;
    private int tried;

    @OneToOne(cascade = CascadeType.ALL)
    private Hint lastHint;

    public Round(String word) {
        this.wordToGuess = word;
        this.tried = 0;
        this.lastHint = new Hint(word, true);
    }

    public Feedback guess(String guess) {
        this.tried++;

        List<Mark> marks = new ArrayList<>();
        String presentLetters = wordToGuess;
        String lowerCaseGuess = guess == null ? "" : guess.toLowerCase();

        if (guess == null || guess.length() == 0 || guess.length() != wordToGuess.length() || lowerCaseGuess.charAt(0) != wordToGuess.charAt(0)) {
            for (int i = 0; i < wordToGuess.length(); i++) {
                marks.add(Mark.ILLEGAL);
            }
            return new Feedback(guess, marks);
        }

        for (int i = 0; i < lowerCaseGuess.length(); i++) {
            String charAsString = lowerCaseGuess.charAt(i)+"";
            if (lowerCaseGuess.charAt(i) == wordToGuess.charAt(i)) {
                marks.add(Mark.CORRECT);
                presentLetters = presentLetters.replaceFirst(charAsString, "");
                continue;
            }
            if (presentLetters.contains(charAsString)) {
                marks.add(Mark.PRESENT);
                presentLetters = presentLetters.replaceFirst(charAsString, "");
                continue;
            }
            marks.add(Mark.WRONG);
        }
        Feedback feedback = new Feedback(guess, marks);
        this.lastHint = this.lastHint.appendFeedback(feedback);

        return feedback;
    }
}

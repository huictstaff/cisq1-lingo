package nl.hu.cisq1.lingo.trainer.domain;

import nl.hu.cisq1.lingo.trainer.domain.enums.Mark;
import nl.hu.cisq1.lingo.trainer.exception.InvalidFeedbackException;

import java.util.ArrayList;
import java.util.List;

public class ConvertGuessToMarks {
    //ToDo:
    // Mark.PRESENT conditions should be more clear, in the current state it just checks if the character is in the word
    //  - if a character only appears once in a word but it appears more than once in the guess it should only be PRESENT on the first appearance in the word
    //  - if a character in the word is already CORRECT, the second appearance if this character can't be PRESENT
    public static List<Mark> converter(String wordToGuess, String guess) {
        List<Mark> marks = new ArrayList<>();
        if(wordToGuess.length() != guess.length()) {
            throw new InvalidFeedbackException("Length is of the guess is not the same as the word to guess");
        }
        for(int i = 0; i < wordToGuess.length(); i++) {
           if(wordToGuess.charAt(i) == guess.charAt(i)) {
               marks.add(Mark.CORRECT);
           } else {
               if (wordToGuess.indexOf(guess.charAt(i)) != -1) {
                   marks.add(Mark.PRESENT);
               } else {
                   marks.add(Mark.ABSENT);
               }
           }
        }
        return marks;
    }
}

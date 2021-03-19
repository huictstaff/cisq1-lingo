package nl.hu.cisq1.lingo.trainer.domain;

import nl.hu.cisq1.lingo.trainer.domain.exception.InvalidCharacterException;
import nl.hu.cisq1.lingo.trainer.domain.exception.InvalidGuessLengthException;

public class Guess {
    private String guess;

    public Guess(String guess) {
        char[] chars = guess.toCharArray();
        for (char character : chars) {
            if (!Character.isLetter(character)) {
                throw new InvalidCharacterException();
            }
        }
        this.guess = guess;
    }

    public String getGuess() {
        return guess;
    }

    private boolean composedOfLetters(String guess) {
        char[] chars = guess.toCharArray();
        for (char character : chars) {
            if (!Character.isLetter(character)) {
                return false;
            }
        }
        return true;
    }

    private boolean validateLength(String guess, String word) {
        return guess.length() == word.length();
    }

    private boolean validateGuess(String guess, String word) {
        return composedOfLetters(guess) && validateLength(guess, word);
    }
}

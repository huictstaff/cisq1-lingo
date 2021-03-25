package nl.hu.cisq1.lingo.trainer.domain;

import lombok.Data;
import nl.hu.cisq1.lingo.trainer.domain.exception.HintException;
import nl.hu.cisq1.lingo.words.domain.Word;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

@Data
public class Hint {

    private List<Character> hintedLetters;


    Hint(List<Character> hintedLetters) {
        this.hintedLetters = hintedLetters;
    }


    public static Hint of(Hint previousHint, Word wordToGuess) throws HintException {
        List<Character> newHintLetters = new ArrayList<>();

        if (wordToGuess.getLength() !=  previousHint.getHintedLetters().size())
            throw new HintException("Word to guess Length can't be longer than that of the previous hint");

        if (!previousHint.getHintedLetters().contains('.'))
            throw new HintException("Word is already guessed");


        int lettersToHint = 1;

        for (int i=0; i< previousHint.getHintedLetters().size(); i++) {
            if (previousHint.hintedLetters.get(i) == '.' && lettersToHint != 0) {
                newHintLetters.add(wordToGuess.getValue().toUpperCase().toCharArray()[i]);
                lettersToHint--;
            } else {
                newHintLetters.add(previousHint.hintedLetters.get(i));
            }
        }
        return new Hint(newHintLetters);
    }

    public static Hint firstHintof(Word word) {
        List<Character> dummyHintChars = new ArrayList<>();
        for (int i=0; i<word.getLength(); i++) {
            dummyHintChars.add('.');
        }
        Hint dummyHint = new Hint(dummyHintChars);
        return Hint.of(dummyHint, word);
    }
}

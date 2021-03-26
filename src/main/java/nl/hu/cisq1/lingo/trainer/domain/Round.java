package nl.hu.cisq1.lingo.trainer.domain;

import lombok.Data;
import nl.hu.cisq1.lingo.trainer.domain.exception.GameRoundException;
import nl.hu.cisq1.lingo.trainer.domain.enums.Mark;
import nl.hu.cisq1.lingo.trainer.domain.utils.Utils;
import nl.hu.cisq1.lingo.words.domain.Word;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Data
public class Round {
    private final Word wordToGuess;
    private final List<Feedback> attempts;

    public Round(Word wordToGuess) {
        this.wordToGuess =  wordToGuess;
        this.attempts = new ArrayList<>();
    }

    private void addFeedback(Feedback feedback) {
        if (this.attempts.size() == this.wordToGuess.getLength())
            throw  GameRoundException.guessAttemptsSurpassWordLength(wordToGuess.getLength());
        this.attempts.add(feedback);
    }


    public void tryAGuess(Word guess) {
        if (numOfAttemptsLeft() > 0 && guess != null) {
            Feedback feedback;


            List<Character> guessLetters = guess
                    .getValue()
                    .toUpperCase()
                    .chars()
                    .mapToObj(e -> (char)e)
                    .collect(Collectors.toList());


            if (wordToGuess.equals(guess)) {
                feedback = Feedback.forCorrect(guess.getValue());
                addFeedback(feedback);
            }

            else if (guess.getLength() != wordToGuess.getLength()
                    || wordToGuess.getValue().toUpperCase().toCharArray()[0] != guessLetters.get(0)) {
                feedback = Feedback.forInvalid(guess.getValue());
                addFeedback(feedback);
            }

            else {
                feedback = genFeedback(guessLetters);
                addFeedback(feedback);
            }

        } else throw GameRoundException.guessAttemptsSurpassWordLength(wordToGuess.getLength());
    }



    Feedback genFeedback(List<Character> wordLetters) {
        Feedback feedback;

        List<Character> WTGLetterList = wordToGuess
                .getValue()
                .toUpperCase()
                .chars()
                .mapToObj(e -> (char)e)
                .collect(Collectors.toList());


        List<Mark> marksList = new ArrayList<>();

        if (wordLetters.size() != WTGLetterList.size()
                || WTGLetterList.get(0) != wordLetters.get(0)) {
            feedback = Feedback.forInvalid(Utils.charsToString(wordLetters));
        } else {
            for (int i = 0; i < wordLetters.size(); i++) {
                if (wordLetters.get(i) == (WTGLetterList.get(i))) {
                    marksList.add(Mark.CORRECT);
                } else if (wordLetters.get(i) != WTGLetterList.get(i)
                        && WTGLetterList.contains(wordLetters.get(i))) {
                    marksList.add(Mark.PRESENT);
                } else if (!WTGLetterList.contains(wordLetters.get(i))) {
                    marksList.add(Mark.ABSENT);
                } else if (!Character.isLetter(wordLetters.get(i))) {
                    marksList.add(Mark.INVALID);
                }
            }
            feedback = new Feedback(Utils.charsToString(wordLetters), marksList);
        }
        return feedback;
    }



    public Hint getAHint() {
        if (!roundIsRunning()) throw GameRoundException.cantGetHintsForAnEndedRound();
        Hint hint =  Hint.of(getHintedLetters(), this.wordToGuess);
        Feedback feedback = genFeedback(hint.getHintedLetters());
        addFeedback(feedback);
        return hint;
    }

    public Hint getHintedLetters() {
        if (attempts.isEmpty()) {
            return Hint.firstHintof(wordToGuess);
        }

        List<Mark> marks = getLatestFeedback().getMarks();
        List<Character> hintedLettersList = new ArrayList<>();

        for (int i=0; i<marks.size(); i++) {
            if (marks.get(i)==Mark.CORRECT)
                hintedLettersList.add(wordToGuess.getValue().toUpperCase().toCharArray()[i]);
            else hintedLettersList.add('.');
        }

        return new Hint(hintedLettersList);
    }

    public boolean isGuessed() {
        if (attempts.isEmpty()) return false;
        return (getLatestFeedback().isWordGuessed()) ? true : false;
    }


    public boolean roundIsRunning() {
        return (numOfAttemptsLeft() == 0 || isGuessed()) ? false : true;
    }



    public int numOfTriedAttempts() {
        return this.attempts.size();
    }

    public int numOfAttemptsLeft() {
        return this.wordToGuess.getLength() - 1 - numOfTriedAttempts();
    }

    public Feedback getLatestFeedback() {
        if (attempts.isEmpty()) {
            throw GameRoundException.noAttemptsYet();
        }

        return attempts.get(attempts.size() - 1);
    }
}

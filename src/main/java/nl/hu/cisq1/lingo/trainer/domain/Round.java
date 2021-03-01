package nl.hu.cisq1.lingo.trainer.domain;

import lombok.Data;
import nl.hu.cisq1.lingo.trainer.domain.exception.GameRoundException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Data
public class Round {
    private final String WORD_TO_GUESS;
    private final List<Feedback> attempts;
    private int hints_left = 3;

    public Round(String wordToGuess) {
        this.WORD_TO_GUESS = Objects.requireNonNull(wordToGuess, "wordToGuess must not be null");
        attempts = new ArrayList<>();
    }

    private void addFeedback(Feedback feedback) {
        if (this.attempts.size() == this.WORD_TO_GUESS.length())
            throw  GameRoundException.guessAttemptsSurpassWordLength(attempts.size(), WORD_TO_GUESS.length());
        this.attempts.add(feedback);
    }

    private List<Character> convertStringToCharList(String str) {
        List<Character> chars = new ArrayList<>();
        for (char ch : str.toCharArray()) {
            chars.add(ch);
        }

        return chars;
    }


    public void tryAGuess(String guess) {
        Feedback feedback;
        guess = Objects.requireNonNull(guess.toLowerCase(), "guess must not be null");

        List<String> WTGAsList = Arrays.asList(WORD_TO_GUESS);
        List<String> guessAsList = Arrays.asList(guess);
        List<Mark> marksList = new ArrayList<>();


        if (WORD_TO_GUESS.equals(guess)) {
            feedback = Feedback.forCorrect(guess);
            addFeedback(feedback);
        }

        if (guess.length() != WORD_TO_GUESS.length()
         || guessAsList.get(0) != WTGAsList.get(0)) {
            feedback = Feedback.forInvalid(guess);
            addFeedback(feedback);
        }

        for (int i=0; i<guess.length(); i++) {
            if (guessAsList.get(i).equals(WTGAsList.get(i))) {
                marksList.add(Mark.CORRECT);
            }
            if (WTGAsList.contains(guessAsList.get(i))) {
                marksList.add(Mark.PRESENT);
            }

            if (!WTGAsList.contains(guessAsList.get(i))) {
                marksList.add(Mark.ABSENT);
            }

            if (!guessAsList.get(i).matches("^[a-zA-Z]*$")) {
                marksList.add(Mark.INVALID);
            }

        }

        feedback = new Feedback(WORD_TO_GUESS, marksList);
        addFeedback(feedback);
    }

    public Hint getHint() {
        if (hints_left > 0) {

            List<Mark> marksList = getLatestFeedback().getMarks();
            List<Integer> shownLettersIndexes = new ArrayList<>();
            List<Character> lettersList = new ArrayList<>();


            for (int i = 0; i < marksList.size(); i++) {
                if (marksList.get(i) != Mark.CORRECT) {
                    shownLettersIndexes.add(i);
                    break;
                }
            }


            for (int i = 0; i < marksList.size(); i++) {
                if (marksList.get(i) == Mark.CORRECT) {
                    shownLettersIndexes.add(i);
                }
            }

            List<Character> WTGLetterList = WORD_TO_GUESS
                    .chars()
                    .mapToObj(e -> (char) e)
                    .collect(Collectors.toList());


            for (int i = 0; i < WTGLetterList.size(); i++) {
                if (shownLettersIndexes.contains(i)) {
                    lettersList.add(WTGLetterList.get(i));
                } else {
                    lettersList.add('-');
                }
            }

            return new Hint(lettersList);

        } else throw GameRoundException.noMoreHintsLeft();
    }


    public int numOfTriedAttempts() {
        return this.attempts.size();

    }

    public int numOfAttemptsLeft() {
        return this.WORD_TO_GUESS.length() - 1 - numOfTriedAttempts();
    }

    public Feedback getLatestFeedback() {
        if (attempts.isEmpty()) {
            throw GameRoundException.noAttemptsYet();
        }

        return attempts.get(attempts.size() - 1);
    }
}

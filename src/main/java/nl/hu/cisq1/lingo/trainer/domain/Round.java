package nl.hu.cisq1.lingo.trainer.domain;

import nl.hu.cisq1.lingo.trainer.domain.exception.AttemptLimitReachedException;
import nl.hu.cisq1.lingo.words.domain.Word;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Round implements Serializable {
    private Word wordToGuess;
    private Integer attempts;
    private List<Feedback> feedbackHistory;
    private String lastHint;

    public Round(Word wordToGuess) {
        this.wordToGuess = wordToGuess;
        this.feedbackHistory = new ArrayList<Feedback>();
        this.lastHint = getBaseHint();
        this.attempts = 0;
    }

    public void guess(String attempt) {
        if(attemptLimitReached()) {
            throw new AttemptLimitReachedException(attempts);
        }
        List<Mark> marks = new ArrayList<Mark>();

        for (int i = 0; i < attempt.length(); i++) {
            if (attempt.length() != wordToGuess.getLength()) {
                marks.add(Mark.INVALID);
                continue;
            }
            if (attempt.charAt(i) == wordToGuess.getValue().charAt(i)) {
                marks.add(Mark.CORRECT);
                continue;
            }
            if (wordToGuess.getValue().indexOf(attempt.charAt(i)) != -1) {
                if (attempt.charAt(wordToGuess.getValue().indexOf(attempt.charAt(i))) == wordToGuess.getValue().charAt(wordToGuess.getValue().indexOf(attempt.charAt(i)))) {
                    marks.add(Mark.ABSENT);
                } else {
                    marks.add(Mark.PRESENT);
                }
            } else {
                marks.add(Mark.ABSENT);
            }
        }
        attempts++;
        feedbackHistory.add(new Feedback(attempt,marks));

    }

    private String getBaseHint() {
        String baseHint = "";
        baseHint += wordToGuess.getValue().charAt(0);
        for(int i = 0; i < wordToGuess.getLength()-1; i++) {
            baseHint += ".";
        }
        return baseHint;
    }

    public String giveHint() {
        if (!feedbackHistory.isEmpty()) {
            this.lastHint = feedbackHistory.get(feedbackHistory.size() - 1).giveHint(lastHint);
        }
        return lastHint;
    }

    public boolean attemptLimitReached() {
        return attempts >= 5;
    }

    public Integer getCurrentWordLength() {
        return wordToGuess.getLength();
    }

    public List<Feedback> getFeedbackHistory() {
        return feedbackHistory;
    }

    public Integer getAttempts() {
        return attempts;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Round round = (Round) o;
        return Objects.equals(wordToGuess, round.wordToGuess) && Objects.equals(attempts, round.attempts) && Objects.equals(feedbackHistory, round.feedbackHistory) && Objects.equals(lastHint, round.lastHint);
    }

    @Override
    public int hashCode() {
        return Objects.hash(wordToGuess, attempts, feedbackHistory, lastHint);
    }
}

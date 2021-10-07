package nl.hu.cisq1.lingo.trainer.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class Round {

    private final String rightWord;
    private final List<Character> wordLetters;
    private int attempts;
    private List<Feedback> FBlist;
    private final String startingHint;

    public Round(String wordToGuess){
        this.rightWord = wordToGuess.toLowerCase(Locale.ROOT);
        this.attempts = 0;
        this.FBlist = new ArrayList<>();
        this.wordLetters = new ArrayList<>();
        this.startingHint = rightWord.toUpperCase(Locale.ROOT).charAt(0) + "?".repeat(Math.max(0, wordToGuess.length() - 1));
        for (char letter : rightWord.toLowerCase(Locale.ROOT).toCharArray()) {
            wordLetters.add(letter);
        }
    }

    public Feedback giveFeedBack(String guessWord){
        this.attempts +=1;
        List<Feedback.Mark> markList = new ArrayList<>();
        List<Character> restOfTheLetters = new ArrayList<>(); //TODO change list type to character //TODO wordToGuess & attempt
        for (int i = 0; i < rightWord.length(); i++) {
            restOfTheLetters.add(wordLetters.get(i));
        }
        StringBuilder hint = new StringBuilder("");
        if (guessWord.length() != rightWord.length()){
            markList.add(Feedback.Mark.INVALID);
            hint.append(startingHint);
        }else {
            int amountLettersRemoved = 0;
            for (int i = 0; i < rightWord.length(); i++) {
                int rlListSize = restOfTheLetters.size();
                if (this.wordLetters.get(i) == guessWord.toLowerCase(Locale.ROOT).charAt(i)) {
                    markList.add(Feedback.Mark.CORRECT);
                    if (!restOfTheLetters.isEmpty()) {
                        System.out.println(restOfTheLetters);
                        restOfTheLetters.remove(i - amountLettersRemoved);
                        hint.append(guessWord.toUpperCase(Locale.ROOT).charAt(i));
                        if (rlListSize > restOfTheLetters.size()) {
                            amountLettersRemoved++;
                        }
                    }
                } else {
                    if (restOfTheLetters.contains(guessWord.charAt(i))) { //TODO only the first encounter should be counted as PRESENT
                        for (int x = 0; i < restOfTheLetters.size(); i++) {
                            if (restOfTheLetters.get(x) == guessWord.charAt(i)) {
                                restOfTheLetters.remove(x);
                                if (rlListSize > restOfTheLetters.size()) {
                                    amountLettersRemoved += 1;
                                }
                            }
                        }
                        markList.add(Feedback.Mark.PRESENT);
                        hint.append("?");
                    } else {
                        markList.add(Feedback.Mark.ABSENT);
                        hint.append("?");
                    }
                }
            }
        }
        Feedback replyFeedBack = new Feedback(guessWord, hint.toString(), markList);
        FBlist.add(replyFeedBack);
        return replyFeedBack;
    }

    @Override
    public String toString() {
        return "Round{" +
                "rightWord='" + rightWord + '\'' +
                ", wordLetters=" + wordLetters +
                ", attempts=" + attempts +
                ", FBlist=" + FBlist +
                ", startingHint='" + startingHint + '\'' +
                '}';
    }
}
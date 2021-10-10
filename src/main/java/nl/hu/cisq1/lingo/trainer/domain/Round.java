package nl.hu.cisq1.lingo.trainer.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Round {

    private final String rightWord;
    private int attempts;
    private final List<Feedback> FBList;
    private final String startingHint;
    private final List<String> hintsList;

    public Round(String wordToGuess){
        this.rightWord = wordToGuess.toLowerCase(Locale.ROOT);
        this.attempts  = 0;
        this.FBList    = new ArrayList<>();
        this.hintsList = new ArrayList<>();
        this.startingHint = rightWord.toUpperCase(Locale.ROOT).charAt(0) + "?".repeat(Math.max(0, wordToGuess.length() - 1));
        this.hintsList.add(startingHint);
    }

    public Feedback inputWord(String guessWord){
        List<Feedback.Mark> markList = new ArrayList<>();
        if(attempts > 4) {
            throw new RuntimeException("Max attempts already reached");
        }else{
            this.attempts += 1;
            char[] wordLetters  = rightWord.toLowerCase(Locale.ROOT).toCharArray();
            if (guessWord.length() != rightWord.length()) {
                markList.add(Feedback.Mark.INVALID);
            } else {
                for (int i = 0; i < rightWord.length(); i++) {
                    char currentLetter = guessWord.toLowerCase(Locale.ROOT).charAt(i);
                    if (wordLetters[i] == currentLetter) {
                        markList.add(Feedback.Mark.CORRECT);
                    } else {
                        markList.add(Feedback.Mark.ABSENT);
                    }
                }
                String dummyRightWord = rightWord;
                for (int i = 0; i < guessWord.length(); i++) {
                    if (markList.get(i) == Feedback.Mark.ABSENT) {
                        for (int a = 0; a < dummyRightWord.length(); a++) {
                            if (markList.get(a) == Feedback.Mark.ABSENT){
                                if (dummyRightWord.charAt(a) == guessWord.charAt(i)){
                                    markList.set(i, Feedback.Mark.PRESENT);
                                    dummyRightWord = findingThePresentChars(dummyRightWord, '?', a);
//                                    System.out.println(dummyRightWord);
                                }
                            }
                        }
                    }
                }
            }
        }
        String hint = makeHint(guessWord, markList);
        Feedback replyFeedBack = new Feedback(guessWord, hint, markList);
        FBList.add(replyFeedBack);
        return replyFeedBack;
    }

    public String makeHint(String attemptedWord, List<Feedback.Mark> list){
        StringBuilder replyString = new StringBuilder();
        for (int i = 0; i < list.size(); i++){
            char currentLetter = attemptedWord.toUpperCase(Locale.ROOT).charAt(i);
            if (list.get(i) == Feedback.Mark.INVALID){
                replyString.append(hintsList.get((hintsList.size()-1)));
            }
            if (list.get(i) == Feedback.Mark.ABSENT){
                replyString.append("?");
            }
            if (list.get(i) == Feedback.Mark.PRESENT){
                replyString.append("!");
            }
            if (list.get(i) == Feedback.Mark.CORRECT){
                replyString.append(currentLetter);
            }
        }
        return replyString.toString();
    }

    public String findingThePresentChars(String str, char ch, int index) {
        char[] chars = str.toCharArray();
        chars[index] = ch;
        return String.valueOf(chars);
    }

    public int getAttempts() {
        return attempts;
    }

    public int getCurrentWordLength() {
        return rightWord.length();
    }

    public String getGuessWord() {
        return this.rightWord;
    }

    public String toString() {
        return "guess word: " + rightWord + ", attempts = " + attempts + ", the starting hint: " + startingHint + ", feedback list: " + FBList;
    }
}
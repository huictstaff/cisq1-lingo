package nl.hu.cisq1.lingo.trainer.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Round {

    private final String rightWord;
    private final List<Character> wordLetters;
    private int attempts;
    private List<Feedback> FBlist;
    private final String startingHint;

    public Round(String wordToGuess){
        this.rightWord = wordToGuess;
        this.attempts = 0;
        this.FBlist = new ArrayList<>();
        this.wordLetters = new ArrayList<>();
        this.startingHint = rightWord.charAt(0) + "?".repeat(Math.max(0, wordToGuess.length() - 1));
        for (char letter : rightWord.toCharArray()) {
            wordLetters.add(letter);
        }
    }

    public Feedback giveFeedBack(String guessWord){
        this.attempts +=1;
        List<Feedback.Mark> markList = new ArrayList<>();
        List<Character> restOfTheLetters = new ArrayList<>(); //TODO change list type to character //TODO wordToGuess & attempt
        for (int i = 0; i < rightWord.length()-1; i++) {
            restOfTheLetters.add(wordLetters.get(i));
        }
        if (guessWord.length() != rightWord.length()){
            markList.add(Feedback.Mark.INVALID);
        }
        for (int i = 0; i < rightWord.length(); i++) {
           if (this.wordLetters.get(i)==guessWord.charAt(i)){
               markList.add(Feedback.Mark.CORRECT);
               restOfTheLetters.remove(i);
           }else{
               if (restOfTheLetters.contains(guessWord.charAt(i))){ //TODO only the first encounter should be counted as PRESENT
                   for (int x = 0; i < restOfTheLetters.size(); i++){
                        if (restOfTheLetters.get(x) == guessWord.charAt(i)){
                            restOfTheLetters.remove(x);
                            break;
                        }
                   }
                   markList.add(Feedback.Mark.PRESENT);
               }else{
                   markList.add(Feedback.Mark.ABSENT);
               }
           }
        }
        Feedback replyFeedBack = new Feedback(guessWord, startingHint, markList);
        FBlist.add(replyFeedBack);
        return replyFeedBack;
    }
}
package nl.hu.cisq1.lingo.trainer.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Round {

    private String wordToGuess;
    private List<String> wordLetters;
    private int attempts;
    private List<Feedback> FBlist;
    private String startingHint;

    public Round(String wordToGuess){
        this.wordToGuess = wordToGuess;
        this.attempts = 0;
        this.FBlist = new ArrayList<>();
        this.wordLetters = new ArrayList<>();
        StringBuilder replyString = new StringBuilder();
        String[] wordSplit = wordToGuess.split("");
        replyString.append(wordSplit[0]);
        replyString.append("?".repeat(Math.max(0, wordToGuess.length() - 1)));
        wordLetters.addAll(Arrays.asList(wordSplit).subList(0, wordToGuess.length()));
        this.startingHint = replyString.toString();
    }

    public Feedback giveFeedBack(String attempt){
        attempt +=1;
        List<Feedback.Mark> markList = new ArrayList<>();
        List<String> restOfTheLetters = this.wordLetters;
        String[] attemptSplit = wordToGuess.split("");

        if (attempt.length() != wordToGuess.length()){
            markList.add(Feedback.Mark.INVALID);
        }

        for (int i = 0; i <= wordToGuess.length(); i++) {
           if (this.wordLetters.get(i).equals(attemptSplit[i])){
               markList.add(Feedback.Mark.CORRECT);
               restOfTheLetters.remove(i);
           }else{
               if (restOfTheLetters.contains(attemptSplit[i])){
                   markList.add(Feedback.Mark.PRESENT);
               }else{
                   markList.add(Feedback.Mark.ABSENT);
               }
           }
        }
        Feedback replyFeedBack = new Feedback(attempt,markList);
        FBlist.add(replyFeedBack);
        return replyFeedBack;
    }
}
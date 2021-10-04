package nl.hu.cisq1.lingo.trainer.domain;

import java.util.ArrayList;
import java.util.List;

public class LingoRound {
    private String toGuess;
    private String previousGuess;
    private int turn;

    public LingoRound(String toGuess){
        this.toGuess = toGuess;
    }
    private List<Mark> getMarks(String attempt){
        List<Mark> markList= new ArrayList<>();
        markList = getCorrectMarks(attempt, markList);

        return markList;
    }

    private List<Mark> getCorrectMarks(String attempt, List<Mark> markList){
        for (int index =0; index<toGuess.length(); index++){
            if (attempt.charAt(index)==toGuess.charAt(index)){
                markList.add(Mark.CORRECT);
            }
            else{
                markList.add(Mark.ABSENT);
            }
        }
        return markList;
    }

    private List<Mark> getPresent(String attempt, List<Mark> markList){
        for (int index =0; index<toGuess.length(); index++){
            if (markList.get(index) != Mark.CORRECT){
                
            }
        }
        return markList;
    }



}

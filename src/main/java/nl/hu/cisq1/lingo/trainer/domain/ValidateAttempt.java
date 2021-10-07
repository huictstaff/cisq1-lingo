package nl.hu.cisq1.lingo.trainer.domain;

import java.util.ArrayList;
import java.util.List;

public class ValidateAttempt {
    private String toGuess;
    private List<Mark> validation = new ArrayList<>();

    public ValidateAttempt(String toGuess) {
        this.toGuess = toGuess;
    }

    public List<Mark> validate(String attempt){
        List<Mark> emptyMarks= new ArrayList<>();
        if (checkInvalid(attempt)){
            return getInvalid(attempt);
        }

        List<Mark> afterCorrect = getCorrect(attempt, emptyMarks);
        return getPresent(attempt, afterCorrect);
    }

    /*part of getMarks*/
    private boolean checkInvalid(String attempt){
        return attempt.length() != toGuess.length();
    }

    /*part of getMarks*/
    private List<Mark> getCorrect(String attempt, List<Mark> markList){
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

    /*part of getMarks*/
    private List<Mark> getPresent(String attempt, List<Mark> markList){
        for (int index =0; index<toGuess.length(); index++){
            if (markList.get(index) != Mark.CORRECT){
                if (toGuess.indexOf(attempt.charAt(index))!= -1 ){
                    if (markList.get(toGuess.indexOf(attempt.charAt(index))) != Mark.CORRECT){
                        markList.set(index, Mark.PRESENT);
                    }
                }
            }
        }
        return markList;
    }

    private List<Mark> getInvalid(String attempt){
        List<Mark> invalidMarks= new ArrayList<>();
        for (int index = 0; index<attempt.length(); index++){
            invalidMarks.add(Mark.INVALID);
        }
        return invalidMarks;
    }

}

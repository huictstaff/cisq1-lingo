package nl.hu.cisq1.lingo.trainer.domain;

import java.util.ArrayList;
import java.util.List;

public class LingoRound {
    private String toGuess;
    private String previousHint;
    private int turn;
    private ValidateAttempt validate;

    public LingoRound(String toGuess){
        this.toGuess = toGuess;
        previousHint = "";
        turn = 0;
        for (int index = 0; index<toGuess.length(); index++){
            previousHint+="-";
        }
        validate = new ValidateAttempt(toGuess);
    }

    public String guess(String attempt){
        turn+=1;
        System.out.println(attempt + " 21");

        List<Mark> markList = getMarks(attempt);

        previousHint = new Feedback(attempt,markList).giveHint(previousHint);

        return previousHint;
    }


    /*part of guess*/
    public List<Mark> getMarks(String attempt){
        return validate.validate(attempt);
    }


    public boolean checkTurns(){
        return turn==5;
    }

}

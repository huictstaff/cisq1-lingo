package nl.hu.cisq1.lingo.trainer.domain;

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


    public boolean gameOver(){
        return turn>=5 && !toGuess.equals(previousHint);
    }

    public boolean win(){
        return toGuess.equals(previousHint);
    }

    public int currentTurn(){
        return turn;
    }

}

package nl.hu.cisq1.lingo.trainer.domain;

import java.util.List;

public class LingoRound {
    private boolean wordIsGuessed;
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
        wordIsGuessed = false;
    }

    public String guess(String attempt){

        System.out.println(attempt + " 21");
        List<Mark> markList = getMarks(attempt);
        previousHint = new Feedback(attempt,markList).giveHint(previousHint);

        System.out.println("turn: "+ turn);
        System.out.println("Your Guess: " + attempt);
        System.out.println("Your Hint is: " + previousHint);
        if (previousHint.equals(toGuess)){
            wordIsGuessed = true;
            return previousHint;
        }

        turn+=1;
        return previousHint;
    }

    public int getTurn() {
        return turn;
    }

    /*part of guess*/
    public List<Mark> getMarks(String attempt){
        return validate.validate(attempt);
    }

    public boolean isWordIsGuessed() {
        return wordIsGuessed;
    }
    public boolean gameOver(){
        return turn==5;
    }
    public int currentTurn(){
        return turn;
    }

}

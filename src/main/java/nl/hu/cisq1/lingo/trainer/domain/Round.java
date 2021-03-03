package nl.hu.cisq1.lingo.trainer.domain;

import lombok.RequiredArgsConstructor;
import nl.hu.cisq1.lingo.trainer.domain.exception.RoundCompletedException;
import nl.hu.cisq1.lingo.words.domain.Word;
import org.jetbrains.annotations.NotNull;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RequiredArgsConstructor
public class Round {
    private final List<Feedback> feedbackList;
    private final List<Turn> turnList;
    private final Validator validator;
    private List<Character> currentHint;
    private Word actualWord;

    private final int maxTurns =5;

    public Feedback activeRound (Word input){
        Turn turn = new Turn(validator, input, actualWord);

        if (turnList.isEmpty()) {
            currentHint=provideStartHint();
        }

        if(!isRoundCompleted()){
            turnList.add(turn);
            //do turn
            Feedback feedback = turn.turn();
            feedbackList.add(feedback);

            List<Character> hint = feedback.giveHint(currentHint, actualWord.getValue());
            return feedback;
        }
        else throw new RoundCompletedException();
    }
    private boolean isRoundCompleted(){
        if(turnList.size()>= maxTurns) return true;
        else return feedbackList.stream().anyMatch(Feedback::isWordGuessed);
    }

    private List<Character> provideStartHint(){
        int r = new Random().nextInt(actualWord.getLength());
        var a = actualWord.getValue().charAt(r);

        var startHint = new ArrayList<Character>();
        for (int i=0;i<actualWord.getLength();i++) startHint.add('.');
        startHint.add(r,a);
        return startHint;
    }
}

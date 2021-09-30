package nl.hu.cisq1.lingo.trainer.domain;

import java.util.ArrayList;
import java.util.List;

public class Feedback {
    private String attempt;
    private List<Mark> FBlist;
    private List<String> hintList;

    public boolean isWordGuessed() {
        for ( Mark feedB : this.FBlist){
            if (feedB == Mark.ABSENT || feedB == Mark.PRESENT || feedB == Mark.INVALID ) {
                return false;
            }
        }
        return true;
    }

    public enum Mark {
        CORRECT,
        ABSENT,
        PRESENT,
        INVALID
    }

    public Feedback(String attempt, List<Mark> list) {
        this.FBlist    = list;
        this.attempt   = attempt;
        this.hintList  = new ArrayList<>();
    }

    public String giveHint(String vorigeHint, String wordToGuess, List<Feedback.Mark> FBList){
        StringBuilder replyString = new StringBuilder();
        String[] attemptSplit = wordToGuess.split("");
        if (hintList.isEmpty()){
            replyString = new StringBuilder(attemptSplit[0]);
            replyString.append("?".repeat(wordToGuess.length()));
            hintList.add(replyString.toString());
        }

        if (hintList.get(hintList.size() - 1).equals(vorigeHint)){
            return vorigeHint;
        }

        //if feedback was just right
        int fbChars =  FBlist.size();
        if (fbChars == wordToGuess.length()){
            for (int i = 1; i < wordToGuess.length(); i++) {
                if (FBlist.get(i) == Mark.CORRECT){
                    replyString.append(attemptSplit[i]);
                }else{
                    replyString.append("?");
                }
            }
        }
        return replyString.toString();
    }
}

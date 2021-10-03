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

    public String giveHint(String lastHint){
        StringBuilder replyString = new StringBuilder();
        String[] attemptSplit = attempt.split("");
        if (hintList.isEmpty()){
            replyString = new StringBuilder(attemptSplit[0]);
            replyString.append("?".repeat(attempt.length()));
            hintList.add(replyString.toString());
        }
        if (hintList.get(hintList.size() - 1).equals(lastHint)){
            return lastHint;
        }
        //if feedback was just right
        int fbChars =  FBlist.size();
        if (fbChars == attempt.length()){
            for (int i = 1; i < attempt.length(); i++) {
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

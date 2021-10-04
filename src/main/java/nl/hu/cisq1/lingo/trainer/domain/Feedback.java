package nl.hu.cisq1.lingo.trainer.domain;

import java.util.ArrayList;
import java.util.List;

public class Feedback {
    private String attempt;
    private List<Mark> markList;
    private List<String> hintList;

    public boolean isWordGuessed() {
        for ( Mark feedB : this.markList){
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
        this.markList = list;
        this.attempt   = attempt;
        this.hintList  = new ArrayList<>();
    }

    public String giveHint(String lastHint){
//        System.out.println(FBlist.toString());
        StringBuilder replyString = new StringBuilder();
        String[] attemptSplit = attempt.split("");
        replyString.append(attemptSplit[0]);
        //if feedback was just right
        int fbChars =  markList.size();

        if (fbChars == attempt.length()){
            for (int i = 1; i < attempt.length(); i++) {
                if (markList.get(i) == Mark.INVALID) {
                    return lastHint;
                }
                if (markList.get(i) == Mark.CORRECT){
                    replyString.append(attemptSplit[i]);
                }else{
                    replyString.append("?");
                }
            }
        }

        if (!hintList.isEmpty()){
            if (hintList.get(hintList.size() - 1).equals(lastHint)) {
                return lastHint;
            }
        }

        return replyString.toString();
    }

    @Override
    public String toString() {
        StringBuilder replyFB = new StringBuilder("word attempted: " + attempt + " Marks: ");
        for (int i = 0; i < attempt.length(); i++) {
            replyFB.append(markList.get(i)).append(" ");
        }
        replyFB.append("Hints: ");
        for (int i = 0; i < attempt.length(); i++) {
            replyFB.append(hintList.get(i)).append(" ");
        }
        return replyFB.toString();
    }
}

package nl.hu.cisq1.lingo.trainer.domain;

import java.util.ArrayList;
import java.util.List;

public class Feedback {
    private String guessWord;
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

    public Feedback(String guessWord, String lastHint, List<Mark> list) {
        this.markList = list;
        this.guessWord = guessWord;
        this.hintList = new ArrayList<>();
        this.hintList.add(lastHint);
    }

    public String giveHint(String lastHint){
        StringBuilder replyString = new StringBuilder();
        replyString.append(guessWord.charAt(0));
        //if feedback was just right
        int fbChars =  markList.size();
        if (markList.contains(Mark.INVALID)){
            return lastHint;
        }
        if (fbChars == guessWord.length()){
            for (int i = 1; i < guessWord.length(); i++) {
                if (markList.get(i) == Mark.CORRECT){
                    replyString.append(guessWord.charAt(i));
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
        StringBuilder replyFB = new StringBuilder("word attempted: " + guessWord + " Marks: ");
        System.out.println(markList.size());

        for (int i = 0; i < guessWord.length(); i++) {
            System.out.println(markList.get(i));
            replyFB.append(markList.get(i)).append(" ");
        }
        replyFB.append("Hints: ");
        for (String s : hintList) {
            replyFB.append(s).append(" ");
        }
        return replyFB.toString();
    }
}

package nl.hu.cisq1.lingo.trainer.domain;

import java.util.List;

public class Feedback {
    private final String guessWord;
    private final List<Mark> markList;
    private final String hint;

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
        this.hint = lastHint;
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
        return replyString.toString();
    }

    public String toString() {
        StringBuilder replyFB = new StringBuilder("word attempted: " + guessWord + ", Marks: ");
        if(!markList.isEmpty()) {
            replyFB.append(markList);
        }
        replyFB.append(", Hint: ");
        replyFB.append(hint);
        return replyFB.toString();
    }
}

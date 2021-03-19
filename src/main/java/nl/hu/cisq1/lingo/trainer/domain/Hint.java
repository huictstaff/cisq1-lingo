package nl.hu.cisq1.lingo.trainer.domain;

import java.util.ArrayList;
import java.util.List;

public class Hint {
    private List<Character> hint;

     public Hint(List<Character> hint) {
         this.hint = hint;
     }

    public void setHint(List<Character> hint) {
        this.hint = hint;
    }

    public List<Character> getHint() {
        return hint;
    }
}

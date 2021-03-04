package nl.hu.cisq1.lingo.trainer.domain;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class Feedback {
    private String guess;
    private List<Mark> marks;

    public boolean wordIsGuessed() {
        if (marks.size() != guess.length()) return false;
        return marks.stream().allMatch(mark -> mark == Mark.CORRECT);
    }

    public boolean guessIsValid() {
        return marks.size() == guess.length();
    }
}

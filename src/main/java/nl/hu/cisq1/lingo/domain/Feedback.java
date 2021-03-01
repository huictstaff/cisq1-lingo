package nl.hu.cisq1.lingo.domain;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import nl.hu.cisq1.lingo.domain.exception.FeedbackInvalidException;
import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode
@ToString
public class Feedback {
    private final String attempt;
    private final List<Mark> marks;

    public Feedback(String attempt, List<Mark> marks) {
        if (attempt.length() != marks.size()) {
            throw new FeedbackInvalidException();
        }

        this.attempt = attempt;
        this.marks = marks;
    }

    public boolean isWordGuessed() {
        return marks.stream().allMatch(mark -> mark.equals(Mark.CORRECT));
    }

    public boolean guessIsInvalid() {
        return marks.stream().allMatch(mark -> mark.equals(Mark.INVALID));
    }

    public List<Character> giveHint() {
        List<Character> characters = new ArrayList<>();
        int index = 0;

        for (Mark mark : this.marks) {
            if (mark.equals(Mark.CORRECT)) {
                characters.add(this.attempt.charAt(index));
            } else {
                characters.add('.');
            }

            index++;
        }

        return characters;
    }
}
package nl.hu.cisq1.lingo.trainer.domain;

import lombok.*;
import nl.hu.cisq1.lingo.trainer.domain.exception.InvalidFeedBackException;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@ToString @EqualsAndHashCode
public class Feedback {
    private final List<Mark> markList;

    public static Feedback correct(String attempt){
        return new Feedback(attempt.chars().mapToObj(x->Mark.CORRECT).collect(Collectors.toList()));
    }
    //setting list correctness externally
    public boolean isWordGuessed() throws InvalidFeedBackException {
        for (Mark m : markList) if (m != Mark.CORRECT) return false;
        return true;
        //if(!marks.isEmpty());
        //return this.marks.stream().allMatch(m-> m==Mark.CORRECT);
    }

    public boolean isGuessValid() {
        return this.markList.stream().allMatch(Predicate.not(Mark.INVALID::equals));
    }

    @NonNull
    public List<Character> giveHint(List<Character> prevHint, String wordToGuess) {
        char[] g = wordToGuess.toCharArray();
        char c;

        if (!isWordGuessed() && isGuessValid()) {
            for (int i = 0; i < markList.size(); i++) {
                c = this.markList.get(i) == Mark.CORRECT ? g[i] : '.';
                prevHint.add(c);
            }
        }
        return prevHint;
    }
}

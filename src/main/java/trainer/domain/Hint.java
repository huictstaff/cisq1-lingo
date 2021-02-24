package trainer.domain;

import lombok.Data;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Data
public class Hint {
    private List<Character> lettersList;

    public Hint(List<Character> lettersList) {
        this.lettersList = lettersList;
    }



    public List<Character> lettersOfHint() {
        return lettersList.stream()
                .filter(c -> !c.equals('.'))
                .collect(Collectors.toList());
    }

    public int getNextHintIndex() {
        return lettersOfHint().size();
    }
}

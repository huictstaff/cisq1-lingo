package nl.hu.cisq1.lingo.trainer.presentation.DTO;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class AttemptDTO {
    private String attempt;

    @JsonCreator
    public AttemptDTO() {
    }

    public AttemptDTO(String attempt) {
        this.attempt = attempt;
    }
}

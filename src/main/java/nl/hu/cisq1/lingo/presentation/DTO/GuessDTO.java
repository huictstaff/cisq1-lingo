package nl.hu.cisq1.lingo.presentation.DTO;


import org.hibernate.validator.constraints.SafeHtml;

import javax.validation.constraints.NotNull;

public class GuessDTO {
    @NotNull
    public String guess;
}

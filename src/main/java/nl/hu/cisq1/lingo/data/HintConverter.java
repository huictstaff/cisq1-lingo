package nl.hu.cisq1.lingo.data;

import nl.hu.cisq1.lingo.domain.Enums.Rating;

import javax.persistence.AttributeConverter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class HintConverter implements AttributeConverter<List<Character>, String> {
    @Override
    public String convertToDatabaseColumn(List<Character> hint){
        return hint.stream()
                .map(Objects::toString)
                .collect((Collectors.joining("")));
    }
    public List<Character> convertToEntityAttribute(String s){
        List<Character> hints = new ArrayList<>();
        for (char ch : s.toCharArray()) {
            hints.add(ch);
        }
        return hints;
    }

}

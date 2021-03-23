package nl.hu.cisq1.lingo.data;

import nl.hu.cisq1.lingo.domain.Enums.Rating;

import javax.persistence.AttributeConverter;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class RatingsConverter implements AttributeConverter<List<Rating>, String> {
    @Override
    public String convertToDatabaseColumn(List<Rating> ratings){
        return ratings.stream()
                .map(Objects::toString)
                .collect((Collectors.joining(",")));
    }
    public List<Rating> convertToEntityAttribute(String s){
        return Arrays.stream(s.split(","))
                .map(Rating::valueOf)
                .collect(Collectors.toList());
    }

}

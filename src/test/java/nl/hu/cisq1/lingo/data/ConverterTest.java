package nl.hu.cisq1.lingo.data;

import nl.hu.cisq1.lingo.domain.Enums.Rating;
import nl.hu.cisq1.lingo.domain.Game;
import nl.hu.cisq1.lingo.domain.exception.ForbiddenRoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ConverterTest {
    @Test
    @DisplayName("Test if converter parses a hint to a string correctly.")
    void converthintToDatabaseColumnTest(){
        HintConverter hintConverter = new HintConverter();
        List<Character> hint = List.of('t','e','s','t');
        assertEquals("test", hintConverter.convertToDatabaseColumn(hint));
    }
    @Test
    @DisplayName("Test if converter parses a string to a hint correctly.")
    void converStringtohint(){
        HintConverter hintConverter = new HintConverter();
        List<Character> hint = List.of('t','e','s','t');
        assertEquals(hint, hintConverter.convertToEntityAttribute("test"));
    }

    @Test
    @DisplayName("Test if converter parses ratings to a string correctly.")
    void convertRatingsToDatabaseColumnTest(){
        RatingsConverter ratingsConverter = new RatingsConverter();
        List<Rating> ratings = List.of(Rating.CORRECT, Rating.ABSENT,Rating.ABSENT,Rating.ABSENT,Rating.ABSENT,Rating.ABSENT);
        assertEquals("CORRECT,ABSENT,ABSENT,ABSENT,ABSENT,ABSENT", ratingsConverter.convertToDatabaseColumn(ratings));
    }
    @Test
    @DisplayName("Test if converter parses a string to ratings correctly.")
    void converStringtoRatings(){
        RatingsConverter ratingsConverter = new RatingsConverter();
        List<Rating> ratings = List.of(Rating.CORRECT, Rating.ABSENT,Rating.ABSENT,Rating.ABSENT,Rating.ABSENT,Rating.ABSENT);
        assertEquals(ratings, ratingsConverter.convertToEntityAttribute("CORRECT,ABSENT,ABSENT,ABSENT,ABSENT,ABSENT"));
    }
}

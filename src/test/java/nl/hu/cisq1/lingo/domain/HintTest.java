package nl.hu.cisq1.lingo.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class HintTest {
	@ParameterizedTest
	@MethodSource("provideHintExamples")
	@DisplayName("test whether the given hint is correct")
	void testGivenHint(Hint previousHint, String wordToGuess, Hint expectedHint){
		List<Mark> marks = List.of(Mark.CORRECT, Mark.PRESENT, Mark.ABSENT, Mark.CORRECT, Mark.CORRECT);

		assertEquals(expectedHint, Hint.giveHint(previousHint, wordToGuess, marks));
	}

	static Stream<Arguments> provideHintExamples(){
		return Stream.of(
				Arguments.of(
						// guess is kegel
						new Hint(List.of("k",".",".",".",".")),
						"kabel",
						new Hint(List.of("k",".",".","e","l")))
		);
	}
}
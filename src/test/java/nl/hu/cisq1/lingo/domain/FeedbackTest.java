package nl.hu.cisq1.lingo.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FeedbackTest {
	@Test
	@DisplayName("word is guessed if all letters are correct")
	void wordIsGuessed(){
		List marks = List.of(Mark.CORRECT, Mark.CORRECT,Mark.CORRECT,Mark.CORRECT,Mark.CORRECT);
		Feedback feedback = new Feedback("woord", marks);

		assertTrue(feedback.isWordGuessed());
	}
}
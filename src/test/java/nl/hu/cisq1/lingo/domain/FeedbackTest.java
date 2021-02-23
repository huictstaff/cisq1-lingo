package nl.hu.cisq1.lingo.domain;

import nl.hu.cisq1.lingo.domain.exceptions.InvalidFeedbackException;
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

	@Test
	@DisplayName("word is not guessed if not all letters are correct")
	void wordIsNotGuessed(){
		List marks = List.of(Mark.ABSENT, Mark.ABSENT,Mark.PRESENT,Mark.ABSENT,Mark.CORRECT);
		Feedback feedback = new Feedback("woord", marks);

		assertFalse(feedback.isWordGuessed());
	}

	@Test
	@DisplayName("the guess is invalid")
	void guessIsInvalid(){
		List marks = List.of(Mark.INVALID, Mark.INVALID,Mark.INVALID,Mark.INVALID,Mark.INVALID,Mark.INVALID);
		Feedback feedback = new Feedback("woord", marks);

		assertFalse(feedback.guessIsValid());
	}

	@Test
	@DisplayName("the guess is valid")
	void guessIsNotInvalid(){
		List marks = List.of(Mark.ABSENT, Mark.ABSENT,Mark.PRESENT,Mark.ABSENT,Mark.CORRECT);
		Feedback feedback = new Feedback("woord", marks);

		assertTrue(feedback.guessIsValid());
	}

	@Test
	@DisplayName("the constructed feedback is invalid")
	void feedbackIsInvalid(){
		assertThrows(
				InvalidFeedbackException.class,
				() -> Feedback.correct("woord", List.of(Mark.CORRECT))
		);
	}
}
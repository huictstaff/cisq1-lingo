package nl.hu.cisq1.lingo.domain;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class Hint {
	private List<String> characters;

	public static Hint giveHint(Hint previousHint, String wordToGuess, List<Mark> marks){
		List<String> list = List.of("k",".",".","e","l");

		return new Hint(list);
	}
}

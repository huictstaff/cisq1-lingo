package nl.hu.cisq1.lingo.domain;

import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
public class Hint {
	private List<Character> characters;

	public static Hint giveHint(Hint previousHint, String wordToGuess, List<Mark> marks){
		List<Character> list = new ArrayList<>();

		for(char c : "abc".toCharArray()) {
			list.add(c);
		}
		return new Hint(list);
	}
}

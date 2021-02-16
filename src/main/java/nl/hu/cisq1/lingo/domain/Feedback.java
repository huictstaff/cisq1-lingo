package nl.hu.cisq1.lingo.domain;

import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class Feedback {
	private String attempt;
	private List<Mark> marks;

	public boolean isWordGuessed() {
		return true;
	}
}

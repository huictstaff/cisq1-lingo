package nl.hu.cisq1.lingo.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@AllArgsConstructor
public class Feedback {
	@Getter
	@NotEmpty
	@NotBlank
	private String attempt;
	@Getter
	private List<Mark> marks;

	public boolean isWordGuessed() {
		return this.marks.stream().allMatch(str -> str.equals(Mark.CORRECT));
	}
}

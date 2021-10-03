package nl.hu.cisq1.lingo.words.domain;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Feedback
{
    String attempt;
    String previousHint;
    List<Mark> marks;

    public Feedback(String attempt, List<Mark> markList)
    {
        this.attempt = attempt;
        this.marks = markList;
    }

    public boolean isWordGuessed()
    {
        return marks.stream().allMatch(m -> m == Mark.CORRECT);
    }

    public boolean guessIsValid()
    {
        if(attempt.length() == 5) {return true;}
        else return false;
    }

    public List<Character> giveHint(String previousHint, String wordToGuess)
    {

        String previousHintWithoutComma = previousHint.replace(",", "");
        char[] ch = previousHintWithoutComma.toCharArray();



        List<Character> charList = new ArrayList<Character>();

        for(int i = 0; i < wordToGuess.length(); i++)
        {
            char guessedLetter = wordToGuess.charAt(i);
            var markResultForLetter = marks.get(i);
            char previousHintLetter = ch[i];

            if(markResultForLetter == Mark.CORRECT)
            {
                charList.add(guessedLetter);
            }
            else
            {
                if(previousHintLetter == '.') {
                    charList.add('.');
                }
                else{
                    charList.add(previousHintLetter);
                }
            }
        }

        return charList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Feedback feedback = (Feedback) o;
        return Objects.equals(attempt, feedback.attempt) && Objects.equals(marks, feedback.marks);
    }

    @Override
    public int hashCode() {
        return Objects.hash(attempt, marks);
    }

    @Override
    public String toString() {
        return "Feedback{" +
                "attempt='" + attempt + '\'' +
                ", mark=" + marks +
                '}';
    }
}

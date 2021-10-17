package nl.hu.cisq1.lingo.lingoTrainer.domain;


import javax.persistence.*;
import java.util.*;

@Entity
public class Feedback
{
    String attempt;

    @javax.persistence.Id
    @GeneratedValue
    private Long Id;

    public ArrayList<Mark> getMarks() {
        return marks;
    }

    public void setMarks(ArrayList<Mark> marks) {
        this.marks = marks;
    }

    ArrayList<Mark> marks = new ArrayList<>();

    public Feedback()
    {
    }

    public boolean isWordGuessed()
    {
        return marks.stream().allMatch(m -> m == Mark.CORRECT);
    }

    public boolean guessIsValid(String attempt)
    {
        if(attempt.length() >= 5) {return true;}
        else return false;
    }

    public List<Character> giveHint(String previousHint, String wordToGuess, String attempt)
    {
        calculateMarks(wordToGuess, attempt);

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

    public List<Mark> calculateMarks(String wordToGuess, String attempt){
        if(marks != null){
            marks.clear();
        }
        else if(marks == null)
        {
            marks = new ArrayList<Mark>();
        }

        for (int i = 0; i < wordToGuess.length(); i++) {

            switch(wordToGuess.charAt(i)) {
                case ',':
                    System.out.println();
                    break;
                case '.':
                    marks.add(Mark.ABSENT);
                    break;
                default:
                    if(wordToGuess.charAt(i) == attempt.charAt(i))
                    {marks.add(Mark.CORRECT);
                    }
                    else
                    {
                        marks.add(Mark.ABSENT);
                    }

            }
        }
        return marks;
    }



    @Override
    public String toString() {
        return "Feedback{" +
                "attempt='" + attempt + '\'' +
                ", mark=" + marks +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Feedback feedback = (Feedback) o;
        return Objects.equals(attempt, feedback.attempt) && Objects.equals(marks, feedback.marks);
    }
}

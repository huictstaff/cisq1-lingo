package nl.hu.cisq1.lingo.trainer.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static nl.hu.cisq1.lingo.trainer.domain.Mark.*;

public class Feedback {
    private String attempt; /** word */
    private List<Mark> marks; /** e.g.: CORRECT, PRESENT, CORRECT, CORRECT, ABSENT */
    private List<String> hint = new ArrayList<>(); /** e.g.: B . . . D*/

    public Feedback() {}

    public Feedback(String attempt, List<Mark> marks) {
        this.attempt = attempt;
        this.marks = marks;
    }

    public Feedback createFeedback(String thisRoundsWord, String guess) {
        List<Mark> markList = new ArrayList<>();

        StringBuilder present = new StringBuilder();
        StringBuilder incorrect = new StringBuilder();

        /** Does the letter in position i of this rounds word equal the letter in position i in the guess */
        for (int i = 0; i < thisRoundsWord.length(); i++) {
            if (!thisRoundsWord.substring(i, i + 1).equals(guess.substring(i, i + 1))) {
                incorrect.append(thisRoundsWord.charAt(i));
            }
        }

        /**  */
        for (int i = 0; i < thisRoundsWord.length(); i++) {
            /** Get letter in position i of guess */
            String letter = guess.substring(i, i + 1);
            /** If letter in position i of guess equals the letter in position i of this rounds word, mark as CORRECT.
             * Also add to present list */
            if (thisRoundsWord.substring(i, i + 1).equals(letter)) {
                markList.add(CORRECT);
                present.append(letter);
            /** If this rounds word contains the letter, on a different position as it did not hit previous if statement,
             * AND a check that count how many of a letter are in the word (eg 2 "o" in "brood") and how many of that
             * letter are already marked as PRESENT
             * AND the letter is in the incorrect list which makes sure it is not ABSENT*/
            } else if (thisRoundsWord.contains(letter)
                    && thisRoundsWord.chars().filter(ch -> ch == letter.charAt(0)).count() > present.chars().filter(ch -> ch == letter.charAt(0)).count()
                    && incorrect.toString().contains(letter)) {

                markList.add(PRESENT);
                present.append(letter);
                /** TODO check if this does work correctly, passes test though */
                incorrect.deleteCharAt(incorrect.indexOf(letter));
            } else {
                /** Whats left is ABSENT */
                markList.add(ABSENT);
            }
        }
        return new Feedback(guess, markList);
    }


    public boolean wordIsGuessed() {
        /** no loops, thank you very much */
        return marks.stream().allMatch(match -> match.equals(CORRECT));
    }

    public boolean guessIsValid() {
        /**  */
        return marks.stream().noneMatch(match -> match.equals(Mark.INVALID));
    }

    public List<String> giveHint(List<String> previousHint, String word) {
        /** TODO cant this be better and prettier */
        for (int i = 0; i < word.length(); i++) {
            if ((marks.get(i) == CORRECT) || (!previousHint.get(i).equals("."))) {
                this.hint.add(word.substring(i, i+1));
            }
            else {
                this.hint.add(".");
            }
        }
        return hint;
    }

    public String getAttempt() {
        return attempt;
    }

    public List<Mark> getMarks() {
        return marks;
    }
}

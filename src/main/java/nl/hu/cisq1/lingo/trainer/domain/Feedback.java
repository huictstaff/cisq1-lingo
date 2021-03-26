package nl.hu.cisq1.lingo.trainer.domain;

import nl.hu.cisq1.lingo.trainer.domain.exception.InvalidCharacterException;
import nl.hu.cisq1.lingo.trainer.domain.exception.InvalidGuessLengthException;
import org.springframework.util.StringUtils;

import javax.validation.constraints.Null;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

public class Feedback {
    private String attempt;
    private List<Mark> marks;

    public Feedback(String attempt) {
        this.attempt = attempt;
        this.marks = new ArrayList<>();
    }

    public Feedback(List<Mark> marks, String attempt) {
        if (isGuessValid(marks)) {
            this.marks = marks;
        } else {
            throw new InvalidCharacterException();
        }

        if (attempt.length() == marks.size()) {
            this.attempt = attempt;
        } else {
            throw new InvalidGuessLengthException();
        }
    }

    // is the list is empty 'stream' will be true.
    public boolean isWordGuessed() {
        return !marks.isEmpty() && this.marks.stream().allMatch(mark -> mark == Mark.CORRECT);
    }

    public boolean isGuessValid(List<Mark> marks) {
        return !marks.isEmpty() && !marks.contains(Mark.INVALID);
    }

    // compares the chars of the char arrays from both parameters and puts the index as key
    // and the char with the corresponding mark in as value wrapped in another TreeMap.
    public TreeMap<Integer, TreeMap<Character, Mark>> prepareFeedback(String word, String guess) {
        char[] cWord = word.toCharArray();
        char[] cGuess = guess.toCharArray();
        TreeMap<Integer, TreeMap<Character, Mark>> feedback = new TreeMap<>();
        for (int i = 0; i < cWord.length; i++) {
            TreeMap<Character, Mark> treeMap = new TreeMap<>();
            if (cWord[i] == cGuess[i]) {
                treeMap.put(cGuess[i], Mark.CORRECT);
            } else {
                for (char c : cGuess) {
                    StringUtils.countOccurrencesOf();
                    System.out.println(word.indexOf(c));
                    System.out.println("\n\n");
                    if (c == cWord[i]) {
//                        if (feedback.get(i).)
                        treeMap.put(c, Mark.PRESENT);
                        feedback.put(i, treeMap);
//                        break;
                    } else if (word.indexOf(c) == -1) {
                        treeMap.put(c, Mark.ABSENT);
                        feedback.put(i, treeMap);
                        break;
                    }
                }
            }
            feedback.put(i, treeMap);
        }


//        char[] cWord = word.toCharArray();
//        char[] cGuess = guess.toCharArray();
//        TreeMap<Integer, TreeMap<Character, Mark>> feedback = new TreeMap<>();
//        for (int i = 0; i < cWord.length; i++) {
//            TreeMap<Character, Mark> treeMap = new TreeMap<>();
//            if (cWord[i] == cGuess[i]) {
//                treeMap.put(cGuess[i], Mark.CORRECT);
//            } else {
//                for (char c : cWord) {
//                    if (c == cGuess[i]) {
//                        treeMap.put(cGuess[i], Mark.PRESENT);
//                        feedback.put(i, treeMap);
//                        break;
//                    }
//                }
//            }
//            feedback.put(i, treeMap);
//        }
        return feedback;
    }

    // Converts the Treemap from the prepareFeedback method to an ArrayList that only consists of Marks.
    public ArrayList<Mark> toMarkArray(TreeMap<Integer, TreeMap<Character, Mark>> feedback) {
        ArrayList<Mark> marksList = new ArrayList<>();
        feedback.values().forEach((i) -> marksList.addAll(i.values()));
        return marksList;
    }

    public ArrayList<Character> toCharArrayList(TreeMap<Integer, TreeMap<Character, Mark>> feedback) {
        ArrayList<Character> chars = new ArrayList<>();
        feedback.values().forEach(i -> chars.addAll(i.keySet()));
        return chars;
    }

    public Hint giveHint(Hint previousHint, String word) {
        ArrayList<Character> hint = new ArrayList<>();
        char[] cWord = word.toCharArray();
        for (int i = 0; i < cWord.length; i++) {
            switch (this.marks.get(i)) {
                case CORRECT:
                    hint.add(cWord[i]);
                    continue;
                case PRESENT:
                    hint.add('+');
                    continue;
                case ABSENT:
                    hint.add('-');
            }
        }
        previousHint.setHint(hint);
        return previousHint;
    }

    public List<Mark> getMarks() {
        return marks;
    }

    public void setMarks(List<Mark> marks) {
        this.marks = marks;
    }
}

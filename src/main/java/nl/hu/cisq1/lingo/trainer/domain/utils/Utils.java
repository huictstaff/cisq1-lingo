package nl.hu.cisq1.lingo.trainer.domain.utils;

import java.util.List;

public class Utils {
    public static String charsToString(List<Character> chars) {
        if (chars==null) throw new IllegalArgumentException("Utils charsToString | character list can't be null");
        StringBuilder sb = new StringBuilder();
        for (Character ch : chars) {
            sb.append(ch);
        }
        return sb.toString();
    }
}

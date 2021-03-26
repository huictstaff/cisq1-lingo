package nl.hu.cisq1.lingo.trainer.domain.utils;

import java.util.List;

public class Utils {
    public static String charsToString(List<Character> chars) {
        StringBuilder sb = new StringBuilder();
        for (Character ch : chars) {
            sb.append(ch);
        }
        return sb.toString();
    }
}

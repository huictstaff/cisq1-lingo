package nl.hu.cisq1.lingo.games.data.Repository;

public interface WordRepository {
    String randomWord(int length);
    boolean validWord(String word);
}

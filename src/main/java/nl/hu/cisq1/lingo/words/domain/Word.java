package nl.hu.cisq1.lingo.words.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity(name = "words")
public class Word {
    @Id
    @Column(name = "word")
    private String value;
    private Integer length;

    public Word() {}
    public Word(String word) {
        this.value = word;
        this.length = word.length();
    }

    public String getValue() {
        return value;
    }

    public Integer getLength() {
        return length;
    }
}

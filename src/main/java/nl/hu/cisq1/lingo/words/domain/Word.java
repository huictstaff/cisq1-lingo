package nl.hu.cisq1.lingo.words.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Word)) return false;
        Word word = (Word) o;
        return Objects.equals(getValue(), word.getValue()) && Objects.equals(getLength(), word.getLength());
    }
}

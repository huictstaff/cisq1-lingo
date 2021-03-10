package nl.hu.cisq1.lingo.application;

import nl.hu.cisq1.lingo.data.SpringWordRepository;
import nl.hu.cisq1.lingo.domain.exception.WordLengthNotSupportedException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class WordService {
    private final SpringWordRepository wordRepository;

    public WordService(SpringWordRepository wordRepository) {
        this.wordRepository = wordRepository;
    }

    public String provideRandomWord(Integer length) {
        return this.wordRepository
                .findRandomWordByLength(length)
                .orElseThrow(() -> new WordLengthNotSupportedException(length))
                .getValue();
    }
}

package trainer.application;

import nl.hu.cisq1.lingo.words.application.WordService;
import trainer.data.SpringGameRepository;

public class TrainerService {
    private WordService wordService;
    private SpringGameRepository gameRepository;

    public TrainerService(WordService wordService, SpringGameRepository gameRepository) {
        this.wordService = wordService;
        this.gameRepository = gameRepository;
    }
}

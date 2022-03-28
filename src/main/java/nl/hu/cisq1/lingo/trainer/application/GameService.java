package nl.hu.cisq1.lingo.trainer.application;

import nl.hu.cisq1.lingo.trainer.data.SpringGameRepository;
import nl.hu.cisq1.lingo.trainer.domain.Game;
import nl.hu.cisq1.lingo.words.application.WordService;
import nl.hu.cisq1.lingo.words.domain.Word;
import org.springframework.stereotype.Service;

@Service
public class GameService {

    private WordService wordService;
    private SpringGameRepository springGameRepository;

    public GameService(WordService wordService, SpringGameRepository springGameRepository) {
        this.wordService = wordService;
        this.springGameRepository = springGameRepository;
    }

    public Game startNewGame() {
        Word word = new Word(wordService.provideRandomWord(5));
        Game game = new Game(word);

        springGameRepository.save(game);

        return game;
    }

    public Game guess(int id, String guess) {
        Game game = getGame(id);
        game.guess(guess);
        springGameRepository.save(game);

        return game;
    }

    public boolean startNewRound() {
        return true;
    }

    public boolean getGameProgress() {
        return true;
    }

    public Game getGame(int id) {
        Game game = springGameRepository.getById(id);
        return game;
    }




}

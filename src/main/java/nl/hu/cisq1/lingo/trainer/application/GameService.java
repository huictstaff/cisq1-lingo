package nl.hu.cisq1.lingo.trainer.application;

import nl.hu.cisq1.lingo.trainer.domain.Game;
import nl.hu.cisq1.lingo.words.application.WordService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class GameService {
    private Game game;
    WordService wordService;

    public Game startGame() {
                game.startGame(wordService.provideRandomWord(5));
        return null;
    }
}

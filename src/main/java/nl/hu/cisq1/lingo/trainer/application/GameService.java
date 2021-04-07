package nl.hu.cisq1.lingo.trainer.application;

import nl.hu.cisq1.lingo.trainer.application.dto.GameDTO;
import nl.hu.cisq1.lingo.trainer.application.exceptions.GameNotFoundException;
import nl.hu.cisq1.lingo.trainer.data.SpringGameRepository;
import nl.hu.cisq1.lingo.trainer.domain.Game;
import nl.hu.cisq1.lingo.words.application.WordService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class GameService {
    private final SpringGameRepository gameRepository;
    private final WordService wordService;

    public GameService(SpringGameRepository gameRepository, WordService wordService) {
        this.gameRepository = gameRepository;
        this.wordService = wordService;
    }

    public GameDTO createGame() {
        String word = wordService.provideRandomWord(5);
        Game game = new Game(word);
        Game savedGame = this.gameRepository.save(game);
        return new GameDTO(savedGame);
    }

    public GameDTO getGame(Long id) throws GameNotFoundException {
        Game game = this.gameRepository.findById(id).orElseThrow(GameNotFoundException::new);
            return new GameDTO(game);
    }

    public GameDTO guess(Long id, String guess) throws GameNotFoundException {
        Game game = this.gameRepository.findById(id).orElseThrow(GameNotFoundException::new);
        game.guess(guess);
        this.gameRepository.save(game);
        return new GameDTO(game);
    }

    public GameDTO newRound(Long id) throws GameNotFoundException {
        Game game = this.gameRepository.findById(id).orElseThrow(GameNotFoundException::new);
        int wordLength = game.nextWordLength();
        String newWord = wordService.provideRandomWord(wordLength);
        game.newRound(newWord);
        this.gameRepository.save(game);
        return new GameDTO(game);
    }
}

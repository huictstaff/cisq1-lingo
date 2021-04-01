package nl.hu.cisq1.lingo.trainer.application;

import nl.hu.cisq1.lingo.trainer.application.dto.GameDTO;
import nl.hu.cisq1.lingo.trainer.data.SpringGameRepository;
import nl.hu.cisq1.lingo.trainer.domain.Game;
import nl.hu.cisq1.lingo.words.application.WordService;
import nl.hu.cisq1.lingo.words.domain.Word;
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
        Game game = new Game(new Word(word));
        this.gameRepository.save(game);
        return new GameDTO(game);
    }

    public GameDTO getGame(Long id) {
        Game game = this.gameRepository.findById(id).orElse(null);
        if (game != null) {
            return new GameDTO(game);
        } return null;
    }

    public GameDTO guess(Long id, String guess) {
        Game game = this.gameRepository.findById(id).orElse(null);
        if (game != null) {
            game.guess(guess);
            this.gameRepository.save(game);
            return new GameDTO(game);
        }
        return null;
    }
}

package nl.hu.cisq1.lingo.trainer.application;

import nl.hu.cisq1.lingo.trainer.application.exception.GameNotFoundException;
import nl.hu.cisq1.lingo.trainer.data.GameEntity;
import nl.hu.cisq1.lingo.trainer.data.SpringGameRepository;
import nl.hu.cisq1.lingo.trainer.domain.Game;
import nl.hu.cisq1.lingo.trainer.domain.Progress;
import nl.hu.cisq1.lingo.words.application.WordService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class TrainerService {
    private final WordService wordService;
    private final SpringGameRepository gameRepository;

    public TrainerService(WordService wordService, SpringGameRepository gameRepository) {
        this.wordService = wordService;
        this.gameRepository = gameRepository;
    }

    public Long startNewGame() {
        Game game = new Game();
        GameEntity gameEntity = new GameEntity(game);
        this.gameRepository.save(gameEntity);
        return gameEntity.getId();
    }

    public Progress startNewRound(Long id) {
        if (this.gameRepository.findById(id).isPresent()) {
            GameEntity gameEntity = this.gameRepository.findById(id).get();
            Game game = gameEntity.getGame();
            String wordToGuess = this.wordService.provideRandomWord(game.getNextWordLength());
            try {
                game.startNewRound(wordToGuess);
                return game.getProgress();
            } catch (Exception e) {
                throw e;
            }
        } else {
            throw new GameNotFoundException();
        }
    }

    public Progress guessWord(Long id, String attempt) {
        if (this.gameRepository.findById(id).isPresent()) {
            GameEntity gameEntity = this.gameRepository.findById(id).get();
            Game game = gameEntity.getGame();

            try {
                game.guessWord(attempt);
                return game.getProgress();
            } catch (Exception e) {
                throw e;
            }
        } else {
            throw new GameNotFoundException();
        }
    }
}

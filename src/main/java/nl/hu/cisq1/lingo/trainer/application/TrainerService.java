package nl.hu.cisq1.lingo.trainer.application;

import nl.hu.cisq1.lingo.trainer.application.exception.GameNotFoundException;
import nl.hu.cisq1.lingo.trainer.data.GameEntity;
import nl.hu.cisq1.lingo.trainer.data.SpringGameRepository;
import nl.hu.cisq1.lingo.trainer.domain.Game;
import nl.hu.cisq1.lingo.trainer.presentation.dto.ProgressDTO;
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

    public ProgressDTO startNewRound(Long id) {
        GameEntity gameEntity = this.gameRepository.findById(id)
                .orElseThrow(GameNotFoundException::new);

        Game game = gameEntity.getGame();
        String wordToGuess = this.wordService.provideRandomWord(game.getNextWordLength());
        game.startNewRound(wordToGuess);

        return new ProgressDTO(
                game.getGameStatus(),
                game.getProgress().getScore(),
                game.getProgress().getCurrentRound(),
                game.getProgress().getFeedbacks());
    }

    public ProgressDTO guessWord(Long id, String attempt) {
        GameEntity gameEntity = this.gameRepository.findById(id)
                .orElseThrow(GameNotFoundException::new);

        Game game = gameEntity.getGame();
        game.guessWord(attempt);

        return new ProgressDTO(
                game.getGameStatus(),
                game.getProgress().getScore(),
                game.getProgress().getCurrentRound(),
                game.getProgress().getFeedbacks());
    }

    public ProgressDTO getProgress(Long id) {
        GameEntity gameEntity = this.gameRepository.findById(id)
                .orElseThrow(GameNotFoundException::new);

        Game game = gameEntity.getGame();

        return new ProgressDTO(
                game.getGameStatus(),
                game.getProgress().getScore(),
                game.getProgress().getCurrentRound(),
                game.getProgress().getFeedbacks());
    }
}
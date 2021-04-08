package nl.hu.cisq1.lingo.trainer.application;


import nl.hu.cisq1.lingo.trainer.data.SpringGameRepository;
import nl.hu.cisq1.lingo.trainer.domain.Feedback;
import nl.hu.cisq1.lingo.trainer.domain.Game;
import nl.hu.cisq1.lingo.trainer.presentation.dto.BeginWord;
import nl.hu.cisq1.lingo.trainer.presentation.dto.GameStatus;
import nl.hu.cisq1.lingo.trainer.presentation.dto.Guess;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class TrainerService {
    private final SpringGameRepository gameRepository;

    public TrainerService(SpringGameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    public GameStatus provideNewGame(BeginWord beginWord) {
        Game game = new Game(beginWord.word);

        this.gameRepository.save(game);

        return new GameStatus(game.getId(), game.getCurrentRound().getLastHint());
    }


    public GameStatus guess(Long id, Guess guess) throws Exception {
        Game game = this.gameRepository.findById(id)
                .orElseThrow(() -> new Exception("Game Not Found"));

        Feedback feedback = game.guess(guess.guess);

        gameRepository.save(game);

        return new GameStatus(game.getId(), feedback, game.getCurrentRound().getLastHint());
    }

    public GameStatus getStatus(Long id) throws Exception {
        Game game = this.gameRepository.findById(id)
                .orElseThrow(() -> new Exception("Game Not Found"));

        return new GameStatus(game.getId(), game.getCurrentRound().getLastHint());
    }
}

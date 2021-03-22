package nl.hu.cisq1.lingo.trainer.application;

import lombok.RequiredArgsConstructor;
import nl.hu.cisq1.lingo.trainer.data.GameRepository;
import nl.hu.cisq1.lingo.trainer.domain.LingoGame;
import nl.hu.cisq1.lingo.trainer.domain.enums.RoundState;
import nl.hu.cisq1.lingo.trainer.exception.GameNotFoundException;
import nl.hu.cisq1.lingo.words.application.WordService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class LingoService {
    private final WordService wordService;
    private final GameRepository gameRepository;

    public LingoGame startGame() {
        LingoGame lingoGame = new LingoGame();
        lingoGame.newRound(wordService.provideRandomWord(lingoGame.generateType().number()));

        this.gameRepository.save(lingoGame);
        return lingoGame;
    }

    public LingoGame makeGuess(String guess, Long gameId) {
        LingoGame lingoGame = getGameById(gameId);

        lingoGame.getLastRound().makeGuessAndGiveHint(guess, this.wordService.wordExistsByValue(guess));

        if (lingoGame.getLastRound().getRoundState() == RoundState.WON) {
            lingoGame.addScore(lingoGame.getLastRound().getTries());
            lingoGame.newRound(wordService.provideRandomWord(lingoGame.generateType().number()));
        }
        this.gameRepository.save(lingoGame);
        return lingoGame;
    }

    private LingoGame getGameById(Long id) {
        if(this.gameRepository.findById(id).isPresent()) {
            return this.gameRepository.findById(id).get();
        }
        else {
            throw new GameNotFoundException("⏺ ⏺ ⏺ ⏺ Game has not started yet! ⏺ ⏺ ⏺ ⏺");
        }
    }
}

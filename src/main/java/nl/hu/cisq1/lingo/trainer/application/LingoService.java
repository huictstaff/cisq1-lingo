package nl.hu.cisq1.lingo.trainer.application;

import lombok.RequiredArgsConstructor;
import nl.hu.cisq1.lingo.trainer.data.Game;
import nl.hu.cisq1.lingo.trainer.data.GameRepository;
import nl.hu.cisq1.lingo.trainer.domain.LingoGame;
import nl.hu.cisq1.lingo.trainer.presentation.dto.GuessDTO;
import nl.hu.cisq1.lingo.words.application.WordService;
import nl.hu.cisq1.lingo.words.data.SpringWordRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class LingoService {
    private final WordService wordService;
    private final GameRepository gameRepository;
    private final SpringWordRepository springWordRepository;

    public LingoGame startGame() {
        LingoGame lingoGame = new LingoGame();
        lingoGame.newRound(wordService.provideRandomWord(lingoGame.generateType().number()));

        this.gameRepository.save(new Game(lingoGame, false));

        return (lingoGame);
    }

    public LingoGame makeGuess(GuessDTO guess) {
        LingoGame lingoGame = this.retrieveLingoGame().getLingoGame();
        lingoGame.getLastRound().checkIfRoundIsLostOrWon();

        lingoGame.getLastRound().makeGuessAndGiveHint(guess.guess, this.springWordRepository.existsByValue(guess.guess));

        switch (lingoGame.getLastRound().getRoundState()) {
            case LOST -> {
                this.gameRepository.save(new Game(lingoGame, true));
                return lingoGame;
            }
            case WON -> {
                lingoGame.newRound(wordService.provideRandomWord(lingoGame.generateType().number()));
                this.gameRepository.save(new Game(lingoGame, false));
                return lingoGame;
            }
            default -> {
                this.gameRepository.save(new Game(lingoGame, false));
                return lingoGame;
            }
        }
    }

    private Game retrieveLingoGame() {
        return this.gameRepository.findTopByGameDoneOrderByIdDesc(false)
                .orElseThrow(() -> new RuntimeException("⏺ ⏺ ⏺ ⏺ Game has not started yet! ⏺ ⏺ ⏺ ⏺"));
    }
}

package nl.hu.cisq1.lingo.trainer.application;

import lombok.RequiredArgsConstructor;
import nl.hu.cisq1.lingo.trainer.domain.Game;
import nl.hu.cisq1.lingo.trainer.presentation.dto.GuessDTO;
import nl.hu.cisq1.lingo.words.application.WordService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LingoService {
    private final WordService wordService;
    private Game game;

    public Game startGame() {
        Game game = new Game();
        game.newRound(wordService.provideRandomWord(game.generateType().number()));
        this.game = game;
        return (game);
    }

    public Game makeGuess(GuessDTO guess) {
        if (!this.game.getGameOver()) {
            this.game.getAllRounds().get(
                    this.game.getAllRounds().size() - 1)
                    .makeGuessAndGiveHint(guess.guess);

            if (this.game.getAllRounds().get(
                    this.game.getAllRounds().size() - 1).isRoundOver()) {
                this.game.newRound(wordService.provideRandomWord(game.generateType().number())); //generate word
            }
        }
        return this.game;
    }
}

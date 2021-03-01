package nl.hu.cisq1.lingo.trainer.application;

import lombok.RequiredArgsConstructor;
import nl.hu.cisq1.lingo.trainer.domain.Game;
import nl.hu.cisq1.lingo.trainer.presentation.dto.GuessDTO;
import nl.hu.cisq1.lingo.trainer.presentation.dto.HintDTO;
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

    public HintDTO makeGuess(GuessDTO guess) {
        System.out.println(this.game.lastRound());
        if (!this.game.getGameOver()) {
            this.game.lastRound().makeGuessAndGiveHint(guess.guess);

            if (this.game.lastRound().isRoundOver()) {
                this.game.newRound(wordService.provideRandomWord(game.generateType().number())); //generate word
            }
        }
        return new HintDTO(
                this.game.lastRound().getTries(),
                this.game.lastRound().getAllFeedback().get(this.game.lastRound().getAllFeedback().size() -1),
                this.game.lastRound().getAllFeedback().get(this.game.lastRound().getAllFeedback().size() -1).giveHint()
        );
    }
}

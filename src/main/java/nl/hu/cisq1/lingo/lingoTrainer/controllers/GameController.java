package nl.hu.cisq1.lingo.lingoTrainer.controllers;

import nl.hu.cisq1.lingo.lingoTrainer.domain.Feedback;
import nl.hu.cisq1.lingo.lingoTrainer.domain.Game;
import nl.hu.cisq1.lingo.lingoTrainer.services.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/")
public class GameController {

    @Autowired
    private GameService gameService;

    @PostMapping("/game")
    public String newGame(@RequestParam String wordToGuess) {

        var game = new Game();
        game.newRound(wordToGuess);

        var gameSaved = gameService.save(game);
        return "New game started with the id: " + gameSaved.Id + " and the word to guess: " + wordToGuess;
    }

    @PostMapping("/game/{id}/guess")
    public String guess(@PathVariable Long id, @RequestParam String guessAttempt) {

        var game = gameService.find(id);

        if (game.getCurrentRound().previousFeedback != null) {
            if (game.getCurrentRound().previousFeedback.isWordGuessed()) {
                return "Word was already guessed, you won!";
            }


            if (game.getCurrentRound().timesGuessed >= 5) {
                return "Round is over, you lost.";
            }
        }
        var feedback = game.getCurrentRound().guessWord(guessAttempt);
        gameService.save(game);
        return feedback.toString();
    }

    @PostMapping("/game/{id}/newRound")
    public String newRound(@PathVariable Long id, @RequestParam String wordToGuess) {

        var game = gameService.find(id);
        game.newRound(wordToGuess);
        gameService.save(game);
        return "New round started!";
    }

    @GetMapping("/game/{id}/gameStatus")
    public String gameStatus(@PathVariable Long id) {
        var game = gameService.find(id);
        return game.toString();
    }
}
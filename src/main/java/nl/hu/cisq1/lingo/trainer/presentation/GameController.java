package nl.hu.cisq1.lingo.trainer.presentation;

import nl.hu.cisq1.lingo.trainer.application.GameService;
import nl.hu.cisq1.lingo.trainer.domain.Game;
import nl.hu.cisq1.lingo.trainer.domain.Progress;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/game")
public class GameController {

    private final GameService gameService;


    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @PostMapping("/start")
    public Progress startGame() {
        return gameService.startNewGame();
    }

    @PostMapping(value = "/{id}")
    public Progress makeGuess(@PathVariable int id, @RequestParam String guess) {
        return gameService.guess(id, guess);
    }

    @PostMapping(value = "/{id}/round")
    public Progress newRound(@PathVariable int id) {
        return gameService.startNewRound(id);
    }

    @GetMapping(value = "/{id}")
    public Progress getGameProgress(@PathVariable int id) {
        return gameService.getGameProgressById(id);
    }

}

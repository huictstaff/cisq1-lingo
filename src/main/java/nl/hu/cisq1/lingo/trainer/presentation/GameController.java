package nl.hu.cisq1.lingo.trainer.presentation;

import nl.hu.cisq1.lingo.trainer.application.GameService;
import nl.hu.cisq1.lingo.trainer.presentation.dto.GuessDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/trainer")
public class GameController {
    private final GameService service;

    public GameController(GameService service) {
        this.service = service;
    }

    @PostMapping("/game")
    public ResponseEntity<?> createGame() {
        return new ResponseEntity<>(this.service.createGame(), HttpStatus.OK);
    }

    @GetMapping("/games/{id}")
    public ResponseEntity<?> getById(@PathVariable int id) {
        return new ResponseEntity<>(this.service.getGame((long) id), HttpStatus.OK);
    }

    @PostMapping("/games/{id}")
    public ResponseEntity<?> guess(@PathVariable int id, @RequestBody GuessDTO guess) {
        return new ResponseEntity<>(this.service.guess((long) id, guess.getGuess()), HttpStatus.OK);
    }
}

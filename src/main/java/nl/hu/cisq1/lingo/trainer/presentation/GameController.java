package nl.hu.cisq1.lingo.trainer.presentation;


import javassist.NotFoundException;
import nl.hu.cisq1.lingo.trainer.application.GameService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import javax.persistence.EntityNotFoundException;


@RestController
@RequestMapping("/lingo")
public class GameController {
    private final GameService service;

    public GameController(GameService service) {
        this.service = service;
    }

    @PostMapping("start")
    public ResponseEntity startGame() {
        try {
            this.service.startGame();
            return new ResponseEntity<>("Game created", HttpStatus.CREATED);
        } catch (Exception exception) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, exception.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity getGameProgress(@PathVariable("id") Long id) {
        try {
            return new ResponseEntity<>(this.service.getProgress(id), HttpStatus.OK);
        } catch (EntityNotFoundException exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, exception.getMessage());
        }
    }

    @PostMapping("/{id}/startRound")
    public ResponseEntity startRound(@PathVariable("id") Long id) {
        try {
            return new ResponseEntity<>(this.service.startNewRound(id), HttpStatus.CREATED);
        } catch (EntityNotFoundException exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, exception.getMessage());
        }
    }

    @PostMapping("/{id}/guess")
    public ResponseEntity guess(@PathVariable("id") Long id, @RequestParam String attempt) {
        try {
            return new ResponseEntity<>(this.service.guess(id,attempt), HttpStatus.OK);
        } catch (EntityNotFoundException exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, exception.getMessage());
        }
    }

    @GetMapping("games")
    public ResponseEntity getAllGames() {
        try {
            return new ResponseEntity<>(this.service.getAllGames(), HttpStatus.OK);
        } catch (NotFoundException exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, exception.getMessage());
        }
    }

}

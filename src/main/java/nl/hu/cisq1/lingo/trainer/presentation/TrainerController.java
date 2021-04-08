package nl.hu.cisq1.lingo.trainer.presentation;

/*
    TODO: Add use cases
        [ ] Start New Game
        [ ] Guess / get hint
        [ ] Request last hint
        [ ] Give up

        Spel start -> GET (/game/start)
          - requires: word
          - returns: gamestatus (id & first hint) || error
        Raad woord -> POST (/game/:id/guess)
          - requires: :id, guess
          - returns: gamestatus (id, feedback & new hint) || error
        Request gamestatus -> GET (/game/:id)
          - requires: :id
          - returns: gamestatus (id, feedback & new hint) || error
        Give up -> DELETE (/game/:id)
          - requires: :id
          - returns: success || error

 */

import nl.hu.cisq1.lingo.trainer.application.TrainerService;

import nl.hu.cisq1.lingo.trainer.presentation.dto.BeginWord;
import nl.hu.cisq1.lingo.trainer.presentation.dto.GameStatus;
import nl.hu.cisq1.lingo.trainer.presentation.dto.Guess;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.websocket.server.PathParam;

@RestController
@RequestMapping("game")
public class TrainerController {

    private final TrainerService service;

    public TrainerController(TrainerService service) {
        this.service = service;
    }

    @PostMapping("start")
    public GameStatus startGame(@RequestBody BeginWord beginWord) {
        return this.service.provideNewGame(beginWord);
    }

    @PostMapping("{id}/guess")
    public GameStatus guessWord(@PathVariable("id") Long id, @RequestBody Guess guess) {
        try {
            return this.service.guess(id, guess);
        } catch (Exception exception) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, exception.getMessage());
        }
    }

    @GetMapping("{id}")
    public GameStatus getStatus(@PathVariable("id") Long id) {
        try {
            return this.service.getStatus(id);
        } catch (Exception exception) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, exception.getMessage());
        }
    }

    @DeleteMapping("{id})")
    public String stopGame(@PathVariable("id") Long id) {
        return "success!";
    }

}

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

import nl.hu.cisq1.lingo.trainer.presentation.dto.GameStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("game")
public class TrainerController {

    private final TrainerService service;

    public TrainerController(TrainerService service) {
        this.service = service;
    }

    @GetMapping("start")
    public GameStatus startGame(@RequestParam String beginWord) {
        return this.service.provideNewGame(beginWord);
    }

}

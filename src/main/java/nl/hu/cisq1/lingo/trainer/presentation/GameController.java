package nl.hu.cisq1.lingo.trainer.presentation;

import nl.hu.cisq1.lingo.trainer.application.GameService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("game")
public class GameController {
    private final GameService service;

    public GameController(GameService service) {
        this.service = service;
    }

//    @PostMapping("new")
//    public String startGame(@RequestParam Integer length) {
//        return this.service.startGame();
//    }
}

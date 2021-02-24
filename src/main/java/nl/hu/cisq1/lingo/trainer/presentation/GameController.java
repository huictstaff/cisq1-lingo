package nl.hu.cisq1.lingo.trainer.presentation;

import lombok.RequiredArgsConstructor;
import nl.hu.cisq1.lingo.trainer.application.LingoService;
import nl.hu.cisq1.lingo.trainer.presentation.dto.GuessDTO;
import nl.hu.cisq1.lingo.trainer.presentation.dto.LingoDTO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("lingo")
@RequiredArgsConstructor
public class GameController {
    private final LingoService lingoService;

    @PostMapping("startgame")
    public LingoDTO startGame() {
        return new LingoDTO(this.lingoService.startGame());
    }

    @PostMapping("guess")
    public LingoDTO makeGuess(@RequestBody GuessDTO guess) {
        return new LingoDTO(this.lingoService.makeGuess(guess));
    }
}

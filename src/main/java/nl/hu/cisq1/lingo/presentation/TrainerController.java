package nl.hu.cisq1.lingo.presentation;

import nl.hu.cisq1.lingo.application.TrainerService;
import nl.hu.cisq1.lingo.data.SpringGameRepository;
import nl.hu.cisq1.lingo.domain.Game;
import nl.hu.cisq1.lingo.domain.exception.ForbiddenRoundException;
import nl.hu.cisq1.lingo.presentation.DTO.GameDTO;
import nl.hu.cisq1.lingo.presentation.DTO.GuessDTO;
import nl.hu.cisq1.lingo.presentation.DTO.ProgresDTO;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/trainer")
public class TrainerController {
    private final TrainerService trainerService;

    public TrainerController(TrainerService trainerService) {
        this.trainerService = trainerService;
    }

    @PostMapping("/game/start")
    public GameDTO startGame(){
        Game game = trainerService.startNewGame();
        return new GameDTO(game);
    }

    //todo Mooiste zou zijn exceptionhandler, maar trycatch is prima hier.
    @PostMapping("/game/{id}/startRound")
    public GameDTO startRound(@PathVariable long id){
            Game game = trainerService.startNewRound(id);
            return new GameDTO(game);
    }

    @PostMapping("/game/{id}/do-guess")
    public ProgresDTO doGuess(@PathVariable long id, @RequestBody GuessDTO guess){
        Game game = trainerService.doGuess(id, guess.guess);
        return new ProgresDTO(game);
    }

}

package nl.hu.cisq1.lingo.trainer.presentation;

import nl.hu.cisq1.lingo.trainer.application.TrainerService;
import nl.hu.cisq1.lingo.trainer.domain.exception.RoundIsOverException;
import nl.hu.cisq1.lingo.trainer.presentation.DTO.AttemptDTO;
import nl.hu.cisq1.lingo.trainer.presentation.DTO.GameDTO;
import nl.hu.cisq1.lingo.trainer.presentation.DTO.HintDTO;
import nl.hu.cisq1.lingo.trainer.presentation.DTO.RoundDTO;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("trainer")
public class TrainerController {
    private final TrainerService trainerService;

    public TrainerController(TrainerService trainerService) {
        this.trainerService = trainerService;
    }

    @PostMapping("game")
    public GameDTO startGame() {
        trainerService.startGame();
        trainerService.saveGame();
        return new GameDTO(trainerService.getGame());
    }

    @PatchMapping("guess")
    public HintDTO guess(@RequestBody AttemptDTO attemptDTO) {
        try {
            HintDTO hintDTO = trainerService.guess(attemptDTO.getAttempt());
            trainerService.saveGame();
            return hintDTO;
        } catch (RoundIsOverException e) {
            throw e;
        }
    }

    @PatchMapping("/round")
    public RoundDTO newRound() {
        RoundDTO roundDTO = trainerService.makeRound();
        trainerService.saveGame();
        return roundDTO;
    }
}

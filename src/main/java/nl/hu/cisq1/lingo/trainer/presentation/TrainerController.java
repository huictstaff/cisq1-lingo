package nl.hu.cisq1.lingo.trainer.presentation;

import nl.hu.cisq1.lingo.trainer.application.TrainerService;
import nl.hu.cisq1.lingo.trainer.application.exception.GameNotFoundException;
import nl.hu.cisq1.lingo.trainer.domain.exception.LostGameException;
import nl.hu.cisq1.lingo.trainer.domain.exception.RoundAlreadyStartedException;
import nl.hu.cisq1.lingo.trainer.domain.exception.RoundNotStartedException;
import nl.hu.cisq1.lingo.trainer.presentation.dto.TrainerResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/trainer")
public class TrainerController {
    private final TrainerService service;

    public TrainerController(TrainerService service) {
        this.service = service;
    }

    @PostMapping("/game")
    public TrainerResponseDTO startNewGame() {
        TrainerResponseDTO response = new TrainerResponseDTO();

        response.gameID = service.startNewGame();
        response.httpStatus = HttpStatus.OK;
        response.progress = service.getProgress(response.gameID);

        return response;
    }

    @PostMapping("/round/{gameID}/")
    public TrainerResponseDTO startNewRound(@PathVariable Long gameID) {
        TrainerResponseDTO response = new TrainerResponseDTO();
        response.gameID = gameID;

        try {
            response.progress = service.startNewRound(gameID);
            response.httpStatus = HttpStatus.OK;
        }
        catch(GameNotFoundException e) {
            response.httpStatus = HttpStatus.NOT_FOUND;
            response.message = "Game with ID " + gameID + " not found!";
        }
        catch(LostGameException e) {
            response.httpStatus = HttpStatus.FORBIDDEN;
            response.message = "You lost the game, you cannot start a new round anymore!";
        }
        catch(RoundAlreadyStartedException e) {
            response.httpStatus = HttpStatus.ALREADY_REPORTED;
            response.message = "You cannot start a new round when the current round is still playing!";
        }

        return response;
    }

    @PostMapping("/guess/{gameID}/{attempt}")
    public TrainerResponseDTO guessWord(@PathVariable Long gameID, @PathVariable String attempt) {
        TrainerResponseDTO response = new TrainerResponseDTO();
        response.gameID = gameID;

        try {
            response.progress = service.guessWord(gameID, attempt);
            response.httpStatus = HttpStatus.OK;
        }
        catch(GameNotFoundException e) {
            response.httpStatus = HttpStatus.NOT_FOUND;
            response.message = "Game with ID " + gameID + " not found!";
        }
        catch(LostGameException e) {
            response.httpStatus = HttpStatus.FORBIDDEN;
            response.message = "You lost the game, you cannot guess anymore!";
        }
        catch(RoundNotStartedException e) {
            response.httpStatus = HttpStatus.PRECONDITION_REQUIRED;
            response.message = "You need to start a new round before you can guess!";
        }

        return response;
    }

    @GetMapping("/progress/{gameID}")
    public TrainerResponseDTO getProgress(@PathVariable Long gameID) {
        TrainerResponseDTO response = new TrainerResponseDTO();
        response.gameID = gameID;

        try {
            response.progress = service.getProgress(gameID);
            response.httpStatus = HttpStatus.OK;
        }
        catch(GameNotFoundException e) {
            response.httpStatus = HttpStatus.NOT_FOUND;
            response.message = "Game with ID " + gameID + " not found!";
        }

        return response;
    }
}
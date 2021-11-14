package nl.hu.cisq1.lingo.trainer.controllers;

import nl.hu.cisq1.lingo.lingoTrainer.domain.exception.WordLengthNotSupportedException;
import nl.hu.cisq1.lingo.trainer.domain.Feedback;
import nl.hu.cisq1.lingo.trainer.domain.Progress;
import nl.hu.cisq1.lingo.trainer.domain.exception.GameDoesNotExistException;
import nl.hu.cisq1.lingo.trainer.services.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/game")
public class GameController
{
    @Autowired
    private GameService gameService;

    @PostMapping("/newGame")
    public Progress newGame()
    {
        try
        {
            return gameService.newGame();
        }
        catch (Exception exception)
        {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, exception.getMessage());
        }
    }

    @PostMapping("/getGameProgress")
    public Progress getGameProgress(@RequestParam long gameId)
    {
        try
        {
            return gameService.getGameProgress(gameId);
        }
        catch (Exception exception)
        {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, exception.getMessage());
        }
    }

    @PostMapping("/newGameRound")
    public Progress newGameRound(@RequestParam long gameId)
    {
        try
        {
            return gameService.newGameRound(gameId);
        }
        catch (Exception exception)
        {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, exception.getMessage());
        }    }

    @PostMapping("/gameGuessWord")
    public Feedback gameGuessWord(@RequestParam long gameId, @RequestParam String wordGuess)
    {
        try
        {
            return gameService.gameGuessWord(gameId, wordGuess);
        }
        catch (Exception exception)
        {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, exception.getMessage());
        }    }

}

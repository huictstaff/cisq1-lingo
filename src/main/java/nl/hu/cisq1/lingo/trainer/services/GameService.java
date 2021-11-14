package nl.hu.cisq1.lingo.trainer.services;

import nl.hu.cisq1.lingo.trainer.domain.*;
import nl.hu.cisq1.lingo.trainer.repositories.GameRepository;
import nl.hu.cisq1.lingo.trainer.repositories.ProgressRepository;
import nl.hu.cisq1.lingo.trainer.repositories.RoundRepository;
import nl.hu.cisq1.lingo.words.application.WordService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class GameService {

    private GameRepository gameRepository;
    private ProgressRepository progressRepository;
    private RoundRepository roundRepository;

    private WordService wordService;

    public GameService(GameRepository gameRepository, ProgressRepository progressRepository, RoundRepository roundRepository, WordService wordService) {
        this.gameRepository = gameRepository;
        this.progressRepository = progressRepository;
        this.roundRepository = roundRepository;
        this.wordService = wordService;
    }


    public Progress newGame()
    {

        // Create a new game instance
        var newGame = new Game();

        // Persist it into the database.
        var savedGame = gameRepository.save(newGame);

        // Create a new progress instance
        var progress = new Progress(savedGame);

        // Persist it into the database.
        var savedProgress = progressRepository.save(progress);

        // Return the new saved progress.
        return savedProgress;
    }

    public Progress newGameRound(long gameId)
    {
        // Get existing game
        var existingGame = gameRepository.findById(gameId).get();

        // for demostration purposes create a new word to guess
        var wordToGuess = wordService.provideRandomWord(5);

        // create a new round
        var newRound = new Round(wordToGuess);

        // save round into database
        var savedRound = roundRepository.save(newRound);

        // Check if game already has a current round
        // if it's true, then save the current round before starting a new round.
        if(existingGame.getCurrentRound() != null)
        {
            existingGame.saveCurrentRound();
        }

        // set round as current round of game
        existingGame.setCurrentRound(savedRound);

        // save changes to database
        gameRepository.save(existingGame);

        // get existing game progress
        var existingProgress = progressRepository.findByGame(existingGame);

        // update game progress
        existingProgress.setGameStatus(GameStatus.ROUND_STARTED_WAITING_FOR_NEW_GUESS);

        // save the updated game progress
        var savedProgress = progressRepository.save(existingProgress);

        return savedProgress;
    }

    public void updateGameProgress(Game existingGame, GameStatus gameStatus)
    {
        // get existing game progress
        var existingProgress = progressRepository.findByGame(existingGame);

        // update the game progress
        existingProgress.setGameStatus(gameStatus);

        // save the updated game progress
        progressRepository.save(existingProgress);
    }

    public Progress getGameProgress(long gameId)
    {
        var existingGame = gameRepository.findById(gameId).get();
        var existingGameProgress = progressRepository.findByGame(existingGame);
        return existingGameProgress;
    }

    public Feedback gameGuessWord(long gameId, String wordGuess)
    {
        // Get existing game
        var existingGame = gameRepository.findById(gameId).get();

        // Get current round
        var currentGameRound = existingGame.getCurrentRound();

        // get feedback based on the word guessed
        var feedback = currentGameRound.GetGuessResult(wordGuess);

        // save changes to Round
        roundRepository.save(currentGameRound);

        // Word is guessed!
        if(feedback.wordIsGuessed())
        {
            // get existing game progress
            var existingProgress = progressRepository.findByGame(existingGame);

            // update the game progress
            existingProgress.setGameStatus(GameStatus.ROUND_STARTED_WAITING_FOR_NEW_GUESS);

            // save the updated game progress
            progressRepository.save(existingProgress);
        }

        return feedback;
    }
}

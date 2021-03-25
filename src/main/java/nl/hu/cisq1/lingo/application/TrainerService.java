package nl.hu.cisq1.lingo.application;

import nl.hu.cisq1.lingo.data.SpringGameRepository;
import nl.hu.cisq1.lingo.domain.Game;
import nl.hu.cisq1.lingo.domain.Round;
import nl.hu.cisq1.lingo.domain.exception.ForbiddenGuessException;
import nl.hu.cisq1.lingo.domain.exception.ForbiddenRoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class TrainerService {
    private WordService wordService;
    private SpringGameRepository gameRepository;

    public TrainerService(WordService wordService, SpringGameRepository gameRepository) {
        this.wordService = wordService;
        this.gameRepository = gameRepository;
    }

    public Game startNewGame(){
        return gameRepository.save(new Game(wordService.provideRandomWord(5)));
    }

    public Game doGuess(long id, String guess){
        Game game = gameRepository.findById(id)
                .orElseThrow(() -> new ForbiddenGuessException("Game not found"));
        Round lastround = game.getLastRound();
        lastround.doGuess(guess);
        return gameRepository.save(game);
    }

    public Game startNewRound(long id){
        Game game = gameRepository.findById(id)
                .orElseThrow(() -> new ForbiddenRoundException("Game not found."));
        int wordLength = game.calculateWordLength();
        game.startNewRound(wordService.provideRandomWord(wordLength));
        System.out.println(game);
        return gameRepository.save(game);
    }
}

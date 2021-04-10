package nl.hu.cisq1.lingo.trainer.application;

import nl.hu.cisq1.lingo.trainer.presentation.DTO.GameDTO;
import nl.hu.cisq1.lingo.trainer.presentation.DTO.HintDTO;
import nl.hu.cisq1.lingo.trainer.presentation.DTO.RoundDTO;
import nl.hu.cisq1.lingo.words.application.WordService;

import nl.hu.cisq1.lingo.trainer.data.SpringGameRepository;
import org.springframework.stereotype.Service;
import nl.hu.cisq1.lingo.trainer.domain.Game;
import nl.hu.cisq1.lingo.trainer.domain.Hint;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class TrainerService {
    private final SpringGameRepository gameRepository;
    private final WordService wordService;
    private Game game;

    public TrainerService(WordService wordService, SpringGameRepository gameRepository) {
        this.gameRepository = gameRepository;
        this.wordService = wordService;
    }

    public GameDTO startGame (){
        Game game = new Game();
        game.makeRound(wordService.provideRandomWord(5));
        this.game = game;
        return new GameDTO(game);
    }

    public RoundDTO makeRound(){
        return new RoundDTO(game.makeRound(wordService.provideRandomWord(5 + game.getRounds().size())));
    }
    public HintDTO guess(String guess){
        return new HintDTO(this.game.guessWord(guess));
    }
    public void saveGame(){
        gameRepository.save(this.game);
    }

    public Optional<Game> findGame(Long id){
        return gameRepository.findById(id);
    }

    public void deleteGameById(Long id){gameRepository.deleteById(id);}

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }
}

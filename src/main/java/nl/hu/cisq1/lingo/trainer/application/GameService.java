package nl.hu.cisq1.lingo.trainer.application;

import nl.hu.cisq1.lingo.trainer.data.SpringGameRepository;
import nl.hu.cisq1.lingo.trainer.domain.Game;
import nl.hu.cisq1.lingo.trainer.domain.Mark;
import nl.hu.cisq1.lingo.trainer.domain.Progress;
import nl.hu.cisq1.lingo.words.application.WordService;
import nl.hu.cisq1.lingo.words.domain.Word;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class GameService {

    private WordService wordService;
    private SpringGameRepository springGameRepository;

    public GameService(WordService wordService, SpringGameRepository springGameRepository) {
        this.wordService = wordService;
        this.springGameRepository = springGameRepository;
    }

    public Progress startNewGame() {
        Word word = new Word(wordService.provideRandomWord(5));
        Game game = new Game(word);

        springGameRepository.save(game);

        return getGameProgress(game);
    }

    public Progress guess(int id, String guess) {
        Game game = getGame(id);
        game.guess(guess);
        springGameRepository.save(game);

        return getGameProgress(game);
    }

    public Progress startNewRound(int id) {
        Game game = getGame(id);

        int lastWordLength = game.getWordLength();

        Word newWord;

        if (lastWordLength < 7) {
            newWord = new Word(wordService.provideRandomWord(lastWordLength+1));
        }
        else {
            newWord = new Word(wordService.provideRandomWord(5));
        }

        game.startNewRound(newWord);
        springGameRepository.save(game);

        return getGameProgress(game);
    }

    /** TODO need something to send to front end user, game object as return value is not good enough */
    /** something to easely get the game data  */
    public Progress getGameProgress(Game game) {
        int roundNumber = game.getRoundFeedback().size();
        System.out.println(roundNumber);
        List<Mark> marks;
        if(roundNumber > 0) {
            marks = game.getRoundFeedback().get(roundNumber-1).getMarks();
        }
        else {
            marks = null;
        }

        System.out.println(marks);

        return new Progress(game.getId(), game.getScore(), game.getGamestate(), game.getRoundFeedback(), roundNumber, marks);
    }

    public Progress getGameProgressById(int id) {
        return getGameProgress(getGame(id));
    }

    public Game getGame(int id) {
        Game game = springGameRepository.getById(id);
        return game;
    }






}

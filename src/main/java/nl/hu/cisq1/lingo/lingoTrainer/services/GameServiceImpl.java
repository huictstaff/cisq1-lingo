package nl.hu.cisq1.lingo.lingoTrainer.services;

import nl.hu.cisq1.lingo.lingoTrainer.domain.Game;
import nl.hu.cisq1.lingo.lingoTrainer.repositories.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

@Service
public class GameServiceImpl implements GameService {

    @Autowired
    private GameRepository gameRepository;

    @Override
    public Game save(Game game) {
        return gameRepository.save(game);
    }

    @Override
    public Game find(Long id) {
        return gameRepository.getOne(id);
    }

    @Override
    public List<Game> findAll() {
        return gameRepository.findAll();
    }

    @Override
    public List<Game> findAll(List<Long> ids) {return gameRepository.findAllById(ids);}

    @Override
    public List<Game> findAll(Sort sort){
        return gameRepository.findAll(sort);
    }

    @Override
    public Page<Game> findAll(Pageable pageable){
        return gameRepository.findAll(pageable);
    }

    @Override
    public void delete(Long id) {
    gameRepository.deleteById(id);
    }

    @Override
    public void delete(List<Game> game) {
        gameRepository.deleteAll(game);
    }

    @Override
    public void delete(Game game) {
        gameRepository.delete(game);
    }

    @Override
    public void deleteAll() {
        gameRepository.deleteAll();
    }

    @Override
    public long count() {
        return gameRepository.count();
    }

}
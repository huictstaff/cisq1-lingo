package nl.hu.cisq1.lingo.lingoTrainer.services;

import nl.hu.cisq1.lingo.lingoTrainer.domain.Game;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface GameService {

    Game save(Game game);

    Game find(Long id);

    List<Game> findAll();

    List<Game> findAll(List<Long> ids);

    List<Game> findAll(Sort sort);

    Page<Game> findAll(Pageable pageable);

    void delete(Long id);

    void delete(List<Game> game);

    void delete(Game game);

    void deleteAll();

    long count();

}
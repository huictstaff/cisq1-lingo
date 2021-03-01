package nl.hu.cisq1.lingo.trainer.data;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GameRepository extends JpaRepository<Game, Long> {
    Optional<Game> findById(Long Id);

    Optional<Game> findTopByGameDoneOrderByIdDesc(Boolean gameDone);
}

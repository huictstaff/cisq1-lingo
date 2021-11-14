package nl.hu.cisq1.lingo.trainer.repositories;

import nl.hu.cisq1.lingo.trainer.domain.Game;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameRepository extends JpaRepository<Game, Long> {
}
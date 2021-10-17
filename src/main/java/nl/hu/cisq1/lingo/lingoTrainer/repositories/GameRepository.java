package nl.hu.cisq1.lingo.lingoTrainer.repositories;

import nl.hu.cisq1.lingo.lingoTrainer.domain.Game;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameRepository extends JpaRepository<Game, Long> {
}
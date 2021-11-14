package nl.hu.cisq1.lingo.trainer.repositories;

import nl.hu.cisq1.lingo.trainer.domain.Round;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoundRepository extends JpaRepository<Round, Long> {
}
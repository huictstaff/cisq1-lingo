package nl.hu.cisq1.lingo.lingoTrainer.repositories;

import nl.hu.cisq1.lingo.lingoTrainer.domain.Round;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoundRepository extends JpaRepository<Round, Long> {
}
package nl.hu.cisq1.lingo.lingoTrainer.repositories;

import nl.hu.cisq1.lingo.lingoTrainer.domain.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FeedbackRepository extends JpaRepository<Feedback, Long> {
}
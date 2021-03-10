package nl.hu.cisq1.lingo.trainer.data;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringGameRepository extends JpaRepository<GameEntity, Long> {
}
package trainer.data;

import nl.hu.cisq1.lingo.words.application.WordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import trainer.domain.Game;

public interface SpringGameRepository extends JpaRepository<Game, Long> {
}

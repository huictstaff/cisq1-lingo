package nl.hu.cisq1.lingo.data;
import nl.hu.cisq1.lingo.domain.Game;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringGameRepository extends JpaRepository<Game, Long> {

}

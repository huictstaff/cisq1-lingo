package nl.hu.cisq1.lingo.trainer;

import nl.hu.cisq1.lingo.trainer.data.GameBlob;
import nl.hu.cisq1.lingo.trainer.data.SpringGameRepository;
import nl.hu.cisq1.lingo.trainer.domain.Game;
import org.springframework.boot.CommandLineRunner;

public class TrainerTestDataFixtures implements CommandLineRunner {
    private final SpringGameRepository repository;

    public TrainerTestDataFixtures(SpringGameRepository repository) {
        this.repository = repository;
    }

    @Override
    public void run(String... args) throws Exception {
        this.repository.save(new GameBlob(1L, new Game("pizza")));
        this.repository.save(new GameBlob(2L, new Game("oranje")));
        this.repository.save(new GameBlob(3L, new Game("wanorde")));
    }
}

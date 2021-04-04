package nl.hu.cisq1.lingo.fixtues;

import nl.hu.cisq1.lingo.data.SpringGameRepository;
import nl.hu.cisq1.lingo.domain.Enums.GameState;
import nl.hu.cisq1.lingo.domain.Game;
import org.springframework.boot.CommandLineRunner;

public class GameTestDataFixtures implements CommandLineRunner {
    private final SpringGameRepository repository;

    public GameTestDataFixtures(SpringGameRepository repository) {
        this.repository = repository;
    }

    @Override
    public void run(String... args) throws Exception {
        Game game = new Game();
        game.setId(1);
        game.setGameState(GameState.PLAYING);
        this.repository.save(game);
    }
}

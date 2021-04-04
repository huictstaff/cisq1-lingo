package nl.hu.cisq1.lingo;

import nl.hu.cisq1.lingo.data.SpringGameRepository;
import nl.hu.cisq1.lingo.fixtues.GameTestDataFixtures;
import nl.hu.cisq1.lingo.fixtues.WordTestDataFixtures;
import nl.hu.cisq1.lingo.data.SpringWordRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

@Profile("ci")
@TestConfiguration
public class CiTestConfiguration {
    @Bean
    CommandLineRunner importWords(SpringWordRepository repository) {
        return new WordTestDataFixtures(repository);
    }
    @Bean
    CommandLineRunner importGames(SpringGameRepository repository) {
        return new GameTestDataFixtures(repository);
    }
}

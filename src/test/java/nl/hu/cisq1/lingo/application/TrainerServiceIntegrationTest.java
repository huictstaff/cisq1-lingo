package nl.hu.cisq1.lingo.application;

import nl.hu.cisq1.lingo.data.SpringWordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
public class TrainerServiceIntegrationTest {
    @Autowired
    private TrainerService trainerService;
    @MockBean
    private SpringWordRepository repository;

}

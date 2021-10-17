package nl.hu.cisq1.lingo.lingoTrainer.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import nl.hu.cisq1.lingo.lingoTrainer.domain.Game;
import nl.hu.cisq1.lingo.lingoTrainer.repositories.GameRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {GameServiceImpl.class})
@ExtendWith(SpringExtension.class)
class GameServiceImplTest {
    @MockBean
    private GameRepository gameRepository;

    @Autowired
    private GameServiceImpl gameServiceImpl;

    @Test
    void testSave() {
        Game game = new Game("Word To Guess");
        when(this.gameRepository.save((Game) any())).thenReturn(game);
        assertSame(game, this.gameServiceImpl.save(new Game("Word To Guess")));
        verify(this.gameRepository).save((Game) any());
        assertTrue(this.gameServiceImpl.findAll().isEmpty());
    }

    @Test
    void testFind() {
        Game game = new Game("Word To Guess");
        when(this.gameRepository.getOne((Long) any())).thenReturn(game);
        assertSame(game, this.gameServiceImpl.find(123L));
        verify(this.gameRepository).getOne((Long) any());
        assertTrue(this.gameServiceImpl.findAll().isEmpty());
    }

    @Test
    void testFindAll() {
        ArrayList<Game> gameList = new ArrayList<Game>();
        when(this.gameRepository.findAll()).thenReturn(gameList);
        List<Game> actualFindAllResult = this.gameServiceImpl.findAll();
        assertSame(gameList, actualFindAllResult);
        assertTrue(actualFindAllResult.isEmpty());
        verify(this.gameRepository).findAll();
    }

    @Test
    void testFindAll2() {
        ArrayList<Game> gameList = new ArrayList<Game>();
        when(this.gameRepository.findAllById((Iterable<Long>) any())).thenReturn(gameList);
        List<Game> actualFindAllResult = this.gameServiceImpl.findAll(new ArrayList<Long>());
        assertSame(gameList, actualFindAllResult);
        assertTrue(actualFindAllResult.isEmpty());
        verify(this.gameRepository).findAllById((Iterable<Long>) any());
        assertTrue(this.gameServiceImpl.findAll().isEmpty());
    }

    @Test
    void testFindAll3() {
        PageImpl<Game> pageImpl = new PageImpl<Game>(new ArrayList<Game>());
        when(this.gameRepository.findAll((Pageable) any())).thenReturn(pageImpl);
        Page<Game> actualFindAllResult = this.gameServiceImpl.findAll((Pageable) null);
        assertSame(pageImpl, actualFindAllResult);
        assertTrue(actualFindAllResult.toList().isEmpty());
        verify(this.gameRepository).findAll((Pageable) any());
        assertTrue(this.gameServiceImpl.findAll().isEmpty());
    }

    @Test
    void testFindAll4() {
        ArrayList<Game> gameList = new ArrayList<Game>();
        when(this.gameRepository.findAll((Sort) any())).thenReturn(gameList);
        List<Game> actualFindAllResult = this.gameServiceImpl.findAll((Sort) null);
        assertSame(gameList, actualFindAllResult);
        assertTrue(actualFindAllResult.isEmpty());
        verify(this.gameRepository).findAll((Sort) any());
        assertTrue(this.gameServiceImpl.findAll().isEmpty());
    }

    @Test
    void testDelete() {
        doNothing().when(this.gameRepository).deleteById((Long) any());
        this.gameServiceImpl.delete(123L);
        verify(this.gameRepository).deleteById((Long) any());
        assertTrue(this.gameServiceImpl.findAll().isEmpty());
    }

    @Test
    void testDelete2() {
        doNothing().when(this.gameRepository).deleteAll((Iterable<? extends Game>) any());
        this.gameServiceImpl.delete(new ArrayList<Game>());
        verify(this.gameRepository).deleteAll((Iterable<? extends Game>) any());
        assertTrue(this.gameServiceImpl.findAll().isEmpty());
    }

    @Test
    void testDelete3() {
        doNothing().when(this.gameRepository).delete((Game) any());
        this.gameServiceImpl.delete(new Game("Word To Guess"));
        verify(this.gameRepository).delete((Game) any());
        assertTrue(this.gameServiceImpl.findAll().isEmpty());
    }

    @Test
    void testDeleteAll() {
        doNothing().when(this.gameRepository).deleteAll();
        this.gameServiceImpl.deleteAll();
        verify(this.gameRepository).deleteAll();
        assertTrue(this.gameServiceImpl.findAll().isEmpty());
    }

    @Test
    void testCount() {
        when(this.gameRepository.count()).thenReturn(3L);
        assertEquals(3L, this.gameServiceImpl.count());
        verify(this.gameRepository).count();
        assertTrue(this.gameServiceImpl.findAll().isEmpty());
    }
}


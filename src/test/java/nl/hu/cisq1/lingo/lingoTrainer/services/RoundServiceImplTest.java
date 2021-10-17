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

import nl.hu.cisq1.lingo.lingoTrainer.domain.Round;
import nl.hu.cisq1.lingo.lingoTrainer.repositories.RoundRepository;
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

@ContextConfiguration(classes = {RoundServiceImpl.class})
@ExtendWith(SpringExtension.class)
class RoundServiceImplTest {
    @MockBean
    private RoundRepository roundRepository;

    @Autowired
    private RoundServiceImpl roundServiceImpl;

    @Test
    void testSave() {
        Round round = new Round("Word To Guess");
        when(this.roundRepository.save((Round) any())).thenReturn(round);
        assertSame(round, this.roundServiceImpl.save(new Round("Word To Guess")));
        verify(this.roundRepository).save((Round) any());
        assertTrue(this.roundServiceImpl.findAll().isEmpty());
    }

    @Test
    void testFind() {
        Round round = new Round("Word To Guess");
        when(this.roundRepository.getOne((Long) any())).thenReturn(round);
        assertSame(round, this.roundServiceImpl.find(123L));
        verify(this.roundRepository).getOne((Long) any());
        assertTrue(this.roundServiceImpl.findAll().isEmpty());
    }

    @Test
    void testFindAll() {
        ArrayList<Round> roundList = new ArrayList<Round>();
        when(this.roundRepository.findAll()).thenReturn(roundList);
        List<Round> actualFindAllResult = this.roundServiceImpl.findAll();
        assertSame(roundList, actualFindAllResult);
        assertTrue(actualFindAllResult.isEmpty());
        verify(this.roundRepository).findAll();
    }

    @Test
    void testFindAll2() {
        PageImpl<Round> pageImpl = new PageImpl<Round>(new ArrayList<Round>());
        when(this.roundRepository.findAll((Pageable) any())).thenReturn(pageImpl);
        Page<Round> actualFindAllResult = this.roundServiceImpl.findAll((Pageable) null);
        assertSame(pageImpl, actualFindAllResult);
        assertTrue(actualFindAllResult.toList().isEmpty());
        verify(this.roundRepository).findAll((Pageable) any());
        assertTrue(this.roundServiceImpl.findAll().isEmpty());
    }

    @Test
    void testFindAll3() {
        ArrayList<Round> roundList = new ArrayList<Round>();
        when(this.roundRepository.findAll((Sort) any())).thenReturn(roundList);
        List<Round> actualFindAllResult = this.roundServiceImpl.findAll((Sort) null);
        assertSame(roundList, actualFindAllResult);
        assertTrue(actualFindAllResult.isEmpty());
        verify(this.roundRepository).findAll((Sort) any());
        assertTrue(this.roundServiceImpl.findAll().isEmpty());
    }

    @Test
    void testDelete() {
        doNothing().when(this.roundRepository).deleteById((Long) any());
        this.roundServiceImpl.delete(123L);
        verify(this.roundRepository).deleteById((Long) any());
        assertTrue(this.roundServiceImpl.findAll().isEmpty());
    }

    @Test
    void testDelete2() {
        doNothing().when(this.roundRepository).deleteAll((Iterable<? extends Round>) any());
        this.roundServiceImpl.delete(new ArrayList<Round>());
        verify(this.roundRepository).deleteAll((Iterable<? extends Round>) any());
        assertTrue(this.roundServiceImpl.findAll().isEmpty());
    }

    @Test
    void testDelete3() {
        doNothing().when(this.roundRepository).delete((Round) any());
        this.roundServiceImpl.delete(new Round("Word To Guess"));
        verify(this.roundRepository).delete((Round) any());
        assertTrue(this.roundServiceImpl.findAll().isEmpty());
    }

    @Test
    void testDeleteAll() {
        doNothing().when(this.roundRepository).deleteAll();
        this.roundServiceImpl.deleteAll();
        verify(this.roundRepository).deleteAll();
        assertTrue(this.roundServiceImpl.findAll().isEmpty());
    }

    @Test
    void testCount() {
        when(this.roundRepository.count()).thenReturn(3L);
        assertEquals(3L, this.roundServiceImpl.count());
        verify(this.roundRepository).count();
        assertTrue(this.roundServiceImpl.findAll().isEmpty());
    }
}


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

import nl.hu.cisq1.lingo.lingoTrainer.domain.Feedback;
import nl.hu.cisq1.lingo.lingoTrainer.domain.Mark;
import nl.hu.cisq1.lingo.lingoTrainer.repositories.FeedbackRepository;
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

@ContextConfiguration(classes = {FeedbackServiceImpl.class})
@ExtendWith(SpringExtension.class)
class FeedbackServiceImplTest {
    @MockBean
    private FeedbackRepository feedbackRepository;

    @Autowired
    private FeedbackServiceImpl feedbackServiceImpl;

    @Test
    void testSave() {
        Feedback feedback = new Feedback();
        feedback.setMarks(new ArrayList<Mark>());
        feedback.calculateMarks("42", "Attempt");
        when(this.feedbackRepository.save((Feedback) any())).thenReturn(feedback);

        Feedback feedback1 = new Feedback();
        feedback1.setMarks(new ArrayList<Mark>());
        feedback1.calculateMarks("42", "Attempt");
        assertSame(feedback, this.feedbackServiceImpl.save(feedback1));
        verify(this.feedbackRepository).save((Feedback) any());
        assertTrue(this.feedbackServiceImpl.findAll().isEmpty());
    }

    @Test
    void testFind() {
        Feedback feedback = new Feedback();
        feedback.setMarks(new ArrayList<Mark>());
        feedback.calculateMarks("42", "Attempt");
        when(this.feedbackRepository.getOne((Long) any())).thenReturn(feedback);
        assertSame(feedback, this.feedbackServiceImpl.find(123L));
        verify(this.feedbackRepository).getOne((Long) any());
        assertTrue(this.feedbackServiceImpl.findAll().isEmpty());
    }

    @Test
    void testFindAll() {
        ArrayList<Feedback> feedbackList = new ArrayList<Feedback>();
        when(this.feedbackRepository.findAll()).thenReturn(feedbackList);
        List<Feedback> actualFindAllResult = this.feedbackServiceImpl.findAll();
        assertSame(feedbackList, actualFindAllResult);
        assertTrue(actualFindAllResult.isEmpty());
        verify(this.feedbackRepository).findAll();
    }

    @Test
    void testFindAll2() {
        PageImpl<Feedback> pageImpl = new PageImpl<Feedback>(new ArrayList<Feedback>());
        when(this.feedbackRepository.findAll((Pageable) any())).thenReturn(pageImpl);
        Page<Feedback> actualFindAllResult = this.feedbackServiceImpl.findAll((Pageable) null);
        assertSame(pageImpl, actualFindAllResult);
        assertTrue(actualFindAllResult.toList().isEmpty());
        verify(this.feedbackRepository).findAll((Pageable) any());
        assertTrue(this.feedbackServiceImpl.findAll().isEmpty());
    }

    @Test
    void testFindAll3() {
        ArrayList<Feedback> feedbackList = new ArrayList<Feedback>();
        when(this.feedbackRepository.findAll((Sort) any())).thenReturn(feedbackList);
        List<Feedback> actualFindAllResult = this.feedbackServiceImpl.findAll((Sort) null);
        assertSame(feedbackList, actualFindAllResult);
        assertTrue(actualFindAllResult.isEmpty());
        verify(this.feedbackRepository).findAll((Sort) any());
        assertTrue(this.feedbackServiceImpl.findAll().isEmpty());
    }

    @Test
    void testDelete() {
        doNothing().when(this.feedbackRepository).deleteById((Long) any());
        this.feedbackServiceImpl.delete(123L);
        verify(this.feedbackRepository).deleteById((Long) any());
        assertTrue(this.feedbackServiceImpl.findAll().isEmpty());
    }

    @Test
    void testDelete2() {
        doNothing().when(this.feedbackRepository).deleteAll((Iterable<? extends Feedback>) any());
        this.feedbackServiceImpl.delete(new ArrayList<Feedback>());
        verify(this.feedbackRepository).deleteAll((Iterable<? extends Feedback>) any());
        assertTrue(this.feedbackServiceImpl.findAll().isEmpty());
    }

    @Test
    void testDelete3() {
        doNothing().when(this.feedbackRepository).delete((Feedback) any());

        Feedback feedback = new Feedback();
        feedback.setMarks(new ArrayList<Mark>());
        feedback.calculateMarks("42", "Attempt");
        this.feedbackServiceImpl.delete(feedback);
        verify(this.feedbackRepository).delete((Feedback) any());
        assertTrue(this.feedbackServiceImpl.findAll().isEmpty());
    }

    @Test
    void testDeleteAll() {
        doNothing().when(this.feedbackRepository).deleteAll();
        this.feedbackServiceImpl.deleteAll();
        verify(this.feedbackRepository).deleteAll();
        assertTrue(this.feedbackServiceImpl.findAll().isEmpty());
    }

    @Test
    void testCount() {
        when(this.feedbackRepository.count()).thenReturn(3L);
        assertEquals(3L, this.feedbackServiceImpl.count());
        verify(this.feedbackRepository).count();
        assertTrue(this.feedbackServiceImpl.findAll().isEmpty());
    }
}


package nl.hu.cisq1.lingo.lingoTrainer.services;

import nl.hu.cisq1.lingo.lingoTrainer.domain.Feedback;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.Collection;
import java.util.List;

public interface FeedbackService {

    Feedback save(Feedback feedback);

    Feedback find(Long id);

    List<Feedback> findAll();

    List<Feedback> findAll(Sort sort);

    Page<Feedback> findAll(Pageable pageable);

    void delete(Long id);

    void delete(List<Feedback> feedback);

    void delete(Feedback feedback);

    void deleteAll();

    long count();

}
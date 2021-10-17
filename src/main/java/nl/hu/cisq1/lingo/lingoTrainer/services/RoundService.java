package nl.hu.cisq1.lingo.lingoTrainer.services;

import nl.hu.cisq1.lingo.lingoTrainer.domain.Round;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface RoundService {

    Round save(Round round);

    Round find(Long id);

    List<Round> findAll();

    List<Round> findAll(Sort sort);

    Page<Round> findAll(Pageable pageable);

    void delete(Long id);

    void delete(List<Round> round);

    void delete(Round round);

    void deleteAll();

    long count();

}
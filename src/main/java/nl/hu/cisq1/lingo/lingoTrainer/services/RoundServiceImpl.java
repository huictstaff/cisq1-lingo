package nl.hu.cisq1.lingo.lingoTrainer.services;

import nl.hu.cisq1.lingo.lingoTrainer.domain.Round;
import nl.hu.cisq1.lingo.lingoTrainer.repositories.RoundRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

@Service
public class RoundServiceImpl implements RoundService {

    @Autowired
    private RoundRepository roundRepository;

    @Override
    public Round save(Round round) {
        return roundRepository.save(round);
    }

    @Override
    public Round find(Long id) {

        return roundRepository.getOne(id);
    }

    @Override
    public List<Round> findAll() {
        return roundRepository.findAll();
    }

    @Override
    public List<Round> findAll(Sort sort){
        return roundRepository.findAll(sort);
    }

    @Override
    public Page<Round> findAll(Pageable pageable){
        return roundRepository.findAll(pageable);
    }

    @Override
    public void delete(Long id) {
    roundRepository.deleteById(id);
    }

    @Override
    public void delete(List<Round> round) {
        roundRepository.deleteAll(round);
    }

    @Override
    public void delete(Round round) {
        roundRepository.delete(round);
    }

    @Override
    public void deleteAll() {
        roundRepository.deleteAll();
    }

    @Override
    public long count() {
        return roundRepository.count();
    }

}
package nl.hu.cisq1.lingo.lingoTrainer.services;

import nl.hu.cisq1.lingo.lingoTrainer.domain.Feedback;
import nl.hu.cisq1.lingo.lingoTrainer.repositories.FeedbackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.Collection;
import java.util.List;

@Service
public class FeedbackServiceImpl implements FeedbackService {

    @Autowired
    private FeedbackRepository feedbackRepository;

    @Override
    public Feedback save(Feedback feedback) {
        return feedbackRepository.save(feedback);
    }

    @Override
    public Feedback find(Long id) {
        return feedbackRepository.getOne(id);
    }

    @Override
    public List<Feedback> findAll() {
        return feedbackRepository.findAll();
    }

    @Override
    public List<Feedback> findAll(Sort sort){
        return feedbackRepository.findAll(sort);
    }

    @Override
    public Page<Feedback> findAll(Pageable pageable){
        return feedbackRepository.findAll(pageable);
    }

    @Override
    public void delete(Long id) {feedbackRepository.deleteById(id);}

    @Override
    public void delete(List<Feedback> feedback) {feedbackRepository.deleteAll(feedback);}

    @Override
    public void delete(Feedback feedback) {
        feedbackRepository.delete(feedback);
    }

    @Override
    public void deleteAll() {
        feedbackRepository.deleteAll();
    }

    @Override
    public long count() {
        return feedbackRepository.count();
    }

}
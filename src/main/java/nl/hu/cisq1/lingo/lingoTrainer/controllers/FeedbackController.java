package nl.hu.cisq1.lingo.lingoTrainer.controllers;

import nl.hu.cisq1.lingo.lingoTrainer.domain.Feedback;
import nl.hu.cisq1.lingo.lingoTrainer.services.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/")
public class FeedbackController {

    @Autowired
    private FeedbackService feedbackService;

    @PostMapping("/feedback")
    public Feedback save(@RequestBody Feedback feedback){
        return feedbackService.save(feedback);
    }

    @GetMapping("/feedback/{id}")
    public Feedback getById(@PathVariable(value = "id") Long id){
        return feedbackService.find(id);
    }

    @GetMapping("/feedback")
    public List<Feedback> getAll(){
        return feedbackService.findAll();
    }

    @DeleteMapping("/feedback/{id}")
    public void deleteById(@PathVariable(value = "id") Long id){
        feedbackService.delete(id);
    }

    @DeleteMapping("/feedback")
    public void deleteAll(){
        feedbackService.deleteAll();
    }

    @GetMapping("/feedback/count")
    public long count(){
        return feedbackService.count();
    }
}
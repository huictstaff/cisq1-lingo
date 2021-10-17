package nl.hu.cisq1.lingo.lingoTrainer.controllers;

import nl.hu.cisq1.lingo.lingoTrainer.domain.Round;
import nl.hu.cisq1.lingo.lingoTrainer.services.RoundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/")
public class RoundController {

    @Autowired
    private RoundService roundService;

    @PostMapping("/round")
    public Round save(@RequestBody Round round){
        return roundService.save(round);
    }

    @GetMapping("/round/{id}")
    public Round getById(@PathVariable(value = "id") Long id){
        return roundService.find(id);
    }

    @GetMapping("/round")
    public List<Round> getAll(){
        return roundService.findAll();
    }

    @DeleteMapping("/round/{id}")
    public void deleteById(@PathVariable(value = "id") Long id){
        roundService.delete(id);
    }

    @DeleteMapping("/round")
    public void deleteAll(){
        roundService.deleteAll();
    }

    @GetMapping("/round/count")
    public long count(){
        return roundService.count();
    }
}
package nl.hu.cisq1.lingo.lingoTrainer.controllers;

import nl.hu.cisq1.lingo.lingoTrainer.domain.Game;
import nl.hu.cisq1.lingo.lingoTrainer.services.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/")
public class GameController {

    @Autowired
    private GameService gameService;

    @PostMapping("/game")
    public Game save(@RequestBody Game game){
        return gameService.save(game);
    }

    @GetMapping("/game/{id}")
    public Game getById(@PathVariable(value = "id") Long id){
        return gameService.find(id);
    }

    @GetMapping("/game")
    public List<Game> getAll(){
        return gameService.findAll();
    }

    @DeleteMapping("/game/{id}")
    public void deleteById(@PathVariable(value = "id") Long id){
        gameService.delete(id);
    }

    @DeleteMapping("/game")
    public void deleteAll(){
        gameService.deleteAll();
    }

    @GetMapping("/game/count")
    public long count(){
        return gameService.count();
    }
}
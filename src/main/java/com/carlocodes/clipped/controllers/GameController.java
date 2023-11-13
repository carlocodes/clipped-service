package com.carlocodes.clipped.controllers;

import com.carlocodes.clipped.dtos.GameActivityDto;
import com.carlocodes.clipped.dtos.GameDto;
import com.carlocodes.clipped.exceptions.ClippedException;
import com.carlocodes.clipped.services.GameService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/game")
public class GameController {
    private final GameService gameService;

    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @PostMapping("/watch-game")
    public ResponseEntity<String> watchGame(@RequestBody GameActivityDto gameActivityDto) throws ClippedException {
        gameService.watchGame(gameActivityDto);
        return ResponseEntity.ok("Game watched!");
    }

    @DeleteMapping("/unwatch-game")
    public ResponseEntity<String> unwatchGame(@RequestBody GameActivityDto gameActivityDto) throws ClippedException {
        gameService.unwatchGame(gameActivityDto);
        return ResponseEntity.ok("Game unwatched!");
    }

    // TODO: Maybe introduce some type of sorting
    @GetMapping("/get-games")
    public ResponseEntity<List<GameDto>> getGames() {
        return ResponseEntity.ok(gameService.getGames());
    }

    @GetMapping("/get-watched-games/user/{id}")
    public ResponseEntity<Set<GameDto>> getWatchedGames(@PathVariable long id) throws ClippedException {
        return ResponseEntity.ok(gameService.getWatchedGames(id));
    }
}

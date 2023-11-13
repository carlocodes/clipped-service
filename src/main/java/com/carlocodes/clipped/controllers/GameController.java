package com.carlocodes.clipped.controllers;

import com.carlocodes.clipped.dtos.GameActivityDto;
import com.carlocodes.clipped.exceptions.ClippedException;
import com.carlocodes.clipped.services.GameService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}

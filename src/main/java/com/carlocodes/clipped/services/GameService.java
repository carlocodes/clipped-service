package com.carlocodes.clipped.services;

import com.carlocodes.clipped.entities.Game;
import com.carlocodes.clipped.repositories.GameRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GameService {
    private final GameRepository gameRepository;

    public GameService(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    public Optional<Game> findById(int id) {
        return gameRepository.findById(id);
    }
}

package com.carlocodes.clipped.services;

import com.carlocodes.clipped.dtos.GameActivityDto;
import com.carlocodes.clipped.entities.Game;
import com.carlocodes.clipped.entities.User;
import com.carlocodes.clipped.exceptions.ClippedException;
import com.carlocodes.clipped.repositories.GameRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class GameService {
    private final GameRepository gameRepository;
    private final UserService userService;

    public GameService(GameRepository gameRepository,
                       UserService userService) {
        this.gameRepository = gameRepository;
        this.userService = userService;
    }

    public void watchGame(GameActivityDto gameActivityDto) throws ClippedException {
        try {
            long userId = gameActivityDto.getUserId();
            int gameId = gameActivityDto.getGameId();

            User user = userService.findById(userId)
                    .orElseThrow(() -> new ClippedException(String.format("User with id: %d does not exist!", userId)));

            Game game = gameRepository.findById(gameId)
                    .orElseThrow(() -> new ClippedException(String.format("Game with id: %d does not exist!", gameId)));

            Set<Game> watchedGames = user.getGames();

            if (watchedGames.contains(game))
                throw new ClippedException(String.format("User with id: %d is already watching game with id: %d!", userId, gameId));

            watchedGames.add(game);
            user.setGames(watchedGames);
            userService.save(user);
        } catch (ClippedException e) {
            throw new ClippedException(String.format("Watch game with id: %d for user with id: %d failed due to %s",
                    gameActivityDto.getGameId(), gameActivityDto.getUserId(), e.getMessage()), e);
        }
    }

    public void unwatchGame(GameActivityDto gameActivityDto) throws ClippedException {
        try {
            long userId = gameActivityDto.getUserId();
            int gameId = gameActivityDto.getGameId();

            User user = userService.findById(userId)
                    .orElseThrow(() -> new ClippedException(String.format("User with id: %d does not exist!", userId)));

            Game game = gameRepository.findById(gameId)
                    .orElseThrow(() -> new ClippedException(String.format("Game with id: %d does not exist!", gameId)));

            Set<Game> watchedGames = user.getGames();

            if (!watchedGames.contains(game))
                throw new ClippedException(String.format("User with id: %d already unwatched game with id: %d!", userId, gameId));

            watchedGames.remove(game);
            user.setGames(watchedGames);
            userService.save(user);
        } catch (ClippedException e) {
            throw new ClippedException(String.format("Unwatch game with id: %d for user with id: %d failed due to %s",
                    gameActivityDto.getGameId(), gameActivityDto.getUserId(), e.getMessage()), e);
        }
    }

    public Optional<Game> findById(int id) {
        return gameRepository.findById(id);
    }
}

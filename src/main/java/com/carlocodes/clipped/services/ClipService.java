package com.carlocodes.clipped.services;

import com.carlocodes.clipped.dtos.ClipDto;
import com.carlocodes.clipped.dtos.LikeDto;
import com.carlocodes.clipped.entities.Clip;
import com.carlocodes.clipped.entities.Game;
import com.carlocodes.clipped.entities.User;
import com.carlocodes.clipped.exceptions.ClippedException;
import com.carlocodes.clipped.mappers.ClipMapper;
import com.carlocodes.clipped.repositories.ClipRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Set;

@Service
public class ClipService {
    private final ClipRepository clipRepository;
    private final UserService userService;
    private final GameService gameService;

    public ClipService(ClipRepository clipRepository,
                       UserService userService,
                       GameService gameService) {
        this.clipRepository = clipRepository;
        this.userService = userService;
        this.gameService = gameService;
    }

    public ClipDto createClip(ClipDto clipDto) throws ClippedException {
        try {
            long userId = clipDto.getUser().getId();
            int gameId = clipDto.getGame().getId();
            String clipUrl = clipDto.getClipUrl();

            User user = userService.findById(userId)
                    .orElseThrow(() -> new ClippedException(String.format("User with id: %d does not exist!", userId)));

            Game game = gameService.findById(gameId)
                    .orElseThrow(() -> new ClippedException(String.format("Game with id: %d does not exist!", gameId)));

            // TODO: Maybe add a check to see if the user is "watching" the game he/she is posting to
            // Throw an exception if he/she is not "watching" the game he/she is posting to
            if (Objects.isNull(clipUrl) || clipUrl.isBlank())
                throw new ClippedException("Clip url should not be null/empty/blank!");

            return ClipMapper.INSTANCE.mapToDto(saveClip(clipDto, user, game));
        } catch (ClippedException e) {
            throw new ClippedException(String.format("Create clip for user with id: %d failed due to %s",
                    clipDto.getUser().getId(), e.getMessage()), e);
        }
    }

    public ClipDto editClip(ClipDto clipDto) throws ClippedException {
        try {
            long clipId = clipDto.getId();
            long userId = clipDto.getUser().getId();

            Clip clip = clipRepository.findById(clipId)
                    .orElseThrow(() -> new ClippedException(String.format("Clip with id: %d does not exist!", clipId)));

            if (!clip.getUser().getId().equals(userId))
                throw new ClippedException(String.format("User with id: %d is not allowed to edit the clip with id: %d!", userId, clipId));

            return ClipMapper.INSTANCE.mapToDto(editClip(clip, clipDto));
        } catch (ClippedException e) {
            throw new ClippedException(String.format("Edit clip with id: %d failed due to %s",
                    clipDto.getId(), e.getMessage()), e);
        }
    }

    public void deleteClip(ClipDto clipDto) throws ClippedException {
        try {
            long clipId = clipDto.getId();
            long userId = clipDto.getUser().getId();

            Clip clip = clipRepository.findById(clipId)
                    .orElseThrow(() -> new ClippedException(String.format("Clip with id: %d does not exist!", clipId)));

            if (!clip.getUser().getId().equals(userId))
                throw new ClippedException(String.format("User with id: %d is not allowed to delete the clip with id: %d!", userId, clipId));

            clipRepository.delete(clip);
        } catch (ClippedException e) {
            throw new ClippedException(String.format("Delete clip with id: %d failed due to %s",
                    clipDto.getId(), e.getMessage()), e);
        }
    }

    public List<ClipDto> getClips(long userId) throws ClippedException {
        try {
            User user = userService.findById(userId)
                    .orElseThrow(() -> new ClippedException(String.format("User with id: %d does not exist!", userId)));

            return ClipMapper.INSTANCE.mapToDtos(clipRepository.findByUser(user));
        } catch (ClippedException e) {
            throw new ClippedException(String.format("Get clips for user with id: %d failed due to %s",
                    userId, e.getMessage()), e);
        }
    }

    public void likeClip(LikeDto likeDto) throws ClippedException {
        try {
            long clipId = likeDto.getClipId();
            long userId = likeDto.getUserId();

            Clip clip = clipRepository.findById(clipId)
                    .orElseThrow(() -> new ClippedException(String.format("Clip with id: %d does not exist!", clipId)));

            User user = userService.findById(userId)
                    .orElseThrow(() -> new ClippedException(String.format("User with id: %d does not exist!", userId)));

            saveLike(clip, user);
        } catch (ClippedException e) {
            throw new ClippedException(String.format("Like clip with id: %d for user with id: %d failed due to %s",
                    likeDto.getClipId(), likeDto.getUserId(), e.getMessage()), e);
        }
    }

    public List<ClipDto> forYou(long userId) throws ClippedException {
        try {
            User user = userService.findById(userId)
                    .orElseThrow(() -> new ClippedException(String.format("User with id: %d does not exist!", userId)));

            // TODO: Update this return
            // TODO: Add a new requirement to distinguish how a clip gets popular
            // What is the criteria for a clip to get tagged as popular?
            return ClipMapper.INSTANCE.mapToDtos(clipRepository.findByUser(user));
        } catch (ClippedException e) {
            throw new ClippedException(String.format("For you for user with id: %d failed due to %s",
                    userId, e.getMessage()), e);
        }
    }

    private Clip saveClip(ClipDto clipDto, User user, Game game) {
        Clip clip = new Clip();

        clip.setClipUrl(clipDto.getClipUrl());
        clip.setMessage(clipDto.getMessage());
        clip.setUser(user);
        clip.setGame(game);

        return clipRepository.save(clip);
    }

    private Clip editClip(Clip clip, ClipDto clipDto) {
        clip.setMessage(clipDto.getMessage());

        return clipRepository.save(clip);
    }

    private void saveLike(Clip clip, User user) {
        Set<User> likes = clip.getLikes();
        likes.add(user);
        clipRepository.save(clip);
    }
}

package com.carlocodes.clipped.services;

import com.carlocodes.clipped.dtos.ClipDto;
import com.carlocodes.clipped.dtos.LikeDto;
import com.carlocodes.clipped.dtos.PostClipRequestDto;
import com.carlocodes.clipped.entities.Clip;
import com.carlocodes.clipped.entities.Game;
import com.carlocodes.clipped.entities.User;
import com.carlocodes.clipped.exceptions.ClippedException;
import com.carlocodes.clipped.mappers.ClipMapper;
import com.carlocodes.clipped.repositories.ClipRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.LinkedHashSet;
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

    public ClipDto postClip(PostClipRequestDto postClipRequestDto) throws ClippedException {
        try {
            long userId = postClipRequestDto.getUserId();
            int gameId = postClipRequestDto.getGameId();
            String url = postClipRequestDto.getUrl();

            User user = userService.findById(userId)
                    .orElseThrow(() -> new ClippedException(String.format("User with id: %d does not exist!", userId)));

            Game game = gameService.findById(gameId)
                    .orElseThrow(() -> new ClippedException(String.format("Game with id: %d does not exist!", gameId)));

            if (Objects.isNull(url) || url.isBlank())
                throw new ClippedException("URL should not be null/empty/blank!");

            Set<Game> watchedGames = user.getGames();

            if (!watchedGames.contains(game))
                throw new ClippedException(String.format("User with id: %d cannot post a clip to a game they are not watching!", userId));

            return ClipMapper.INSTANCE.mapToDto(saveClip(postClipRequestDto, user, game));
        } catch (ClippedException e) {
            throw new ClippedException(String.format("Post clip for user with id: %d to game with id: %d failed due to %s",
                    postClipRequestDto.getUserId(), postClipRequestDto.getGameId(), e.getMessage()), e);
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
            throw new ClippedException(String.format("Edit clip with id: %d for user with id: %d failed due to %s",
                    clipDto.getId(), clipDto.getUser().getId(), e.getMessage()), e);
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
            throw new ClippedException(String.format("Delete clip with id: %d for user with id: %d failed due to %s",
                    clipDto.getId(), clipDto.getUser().getId(), e.getMessage()), e);
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

    public LinkedHashSet<ClipDto> forYou(long userId) throws ClippedException {
        try {
            User user = userService.findById(userId)
                    .orElseThrow(() -> new ClippedException(String.format("User with id: %d does not exist!", userId)));

            // TODO: Update this return by knowing what user likes to see
            // TODO: Revisit this query
            return ClipMapper.INSTANCE.mapToDtos(clipRepository.findByCreatedDateTimeGreaterThanEqualOrderByLikesDescCreatedDateTimeDesc(LocalDateTime.now().minusHours(2)));
        } catch (ClippedException e) {
            throw new ClippedException(String.format("For you for user with id: %d failed due to %s",
                    userId, e.getMessage()), e);
        }
    }

    public LinkedHashSet<ClipDto> watching(long userId) throws ClippedException {
        try {
            User user = userService.findById(userId)
                    .orElseThrow(() -> new ClippedException(String.format("User with id: %d does not exist!", userId)));

            Set<Game> watchedGames = user.getGames();

            if (watchedGames.isEmpty())
                throw new ClippedException(String.format("User with id: %d is not watching any games!", userId));

            // TODO: Revisit this query
            // This query does not order the highest number of likes
            return ClipMapper.INSTANCE.mapToDtos(clipRepository.watching(LocalDateTime.now().minusHours(2), watchedGames));
        } catch (ClippedException e) {
            throw new ClippedException(String.format("Watching for user with id: %d failed due to %s",
                    userId, e.getMessage()), e);
        }
    }

    private Clip saveClip(PostClipRequestDto postClipRequestDto, User user, Game game) {
        Clip clip = new Clip();

        clip.setUser(user);
        clip.setGame(game);
        clip.setTitle(postClipRequestDto.getTitle());
        clip.setDescription(postClipRequestDto.getDescription());
        clip.setUrl(postClipRequestDto.getUrl());

        return clipRepository.save(clip);
    }

    private Clip editClip(Clip clip, ClipDto clipDto) {
        clip.setDescription(clipDto.getDescription());

        return clipRepository.save(clip);
    }

    private void saveLike(Clip clip, User user) {
        Set<User> likes = clip.getLikes();
        likes.add(user);
        clipRepository.save(clip);
    }
}

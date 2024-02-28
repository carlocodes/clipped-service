package com.carlocodes.clipped.dtos;

import java.time.LocalDateTime;
import java.util.Set;

public class ClipDto {
    private Long id;
    private String title;
    private String description;
    private String url;
    private UserDto user;
    private GameDto game;
    private Set<UserDto> likes;
    private LocalDateTime createdDateTime;
    private LocalDateTime updatedDateTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public UserDto getUser() {
        return user;
    }

    public void setUser(UserDto user) {
        this.user = user;
    }

    public GameDto getGame() {
        return game;
    }

    public void setGame(GameDto game) {
        this.game = game;
    }

    public Set<UserDto> getLikes() {
        return likes;
    }

    public void setLikes(Set<UserDto> likes) {
        this.likes = likes;
    }

    public LocalDateTime getCreatedDateTime() {
        return createdDateTime;
    }

    public void setCreatedDateTime(LocalDateTime createdDateTime) {
        this.createdDateTime = createdDateTime;
    }

    public LocalDateTime getUpdatedDateTime() {
        return updatedDateTime;
    }

    public void setUpdatedDateTime(LocalDateTime updatedDateTime) {
        this.updatedDateTime = updatedDateTime;
    }

    @Override
    public String toString() {
        return "ClipDto{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", url='" + url + '\'' +
                ", user=" + user +
                ", game=" + game +
                ", likes=" + likes +
                ", createdDateTime=" + createdDateTime +
                ", updatedDateTime=" + updatedDateTime +
                '}';
    }
}

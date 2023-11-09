package com.carlocodes.clipped.dtos;

import java.time.LocalDateTime;

public class ClipDto {
    private Long id;
    private String clipUrl;
    private String message;
    private UserDto user;
    private GameDto game;
    private LocalDateTime createdDateTime;
    private LocalDateTime updatedDateTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getClipUrl() {
        return clipUrl;
    }

    public void setClipUrl(String clipUrl) {
        this.clipUrl = clipUrl;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
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
                ", clipUrl='" + clipUrl + '\'' +
                ", message='" + message + '\'' +
                ", user=" + user +
                ", game=" + game +
                ", createdDateTime=" + createdDateTime +
                ", updatedDateTime=" + updatedDateTime +
                '}';
    }
}

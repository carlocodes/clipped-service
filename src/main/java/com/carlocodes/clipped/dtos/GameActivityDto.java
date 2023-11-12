package com.carlocodes.clipped.dtos;

// TODO: Revisit class name to be more meaningful and appropriate
public class GameActivityDto {
    private Long userId;
    private Integer gameId;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Integer getGameId() {
        return gameId;
    }

    public void setGameId(Integer gameId) {
        this.gameId = gameId;
    }

    @Override
    public String toString() {
        return "GameActivityDto{" +
                "userId=" + userId +
                ", gameId=" + gameId +
                '}';
    }
}

package com.carlocodes.clipped.dtos;

// TODO: Revisit the name of this class
public class LikeDto {
    private Long clipId;
    private Long userId;

    public Long getClipId() {
        return clipId;
    }

    public void setClipId(Long clipId) {
        this.clipId = clipId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "LikeDto{" +
                "clipId=" + clipId +
                ", userId=" + userId +
                '}';
    }
}

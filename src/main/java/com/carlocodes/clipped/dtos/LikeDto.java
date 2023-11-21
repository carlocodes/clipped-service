package com.carlocodes.clipped.dtos;

// TODO: Revisit the name of this class
public class LikeDto {
    private long clipId;
    private long userId;

    public long getClipId() {
        return clipId;
    }

    public void setClipId(long clipId) {
        this.clipId = clipId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
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

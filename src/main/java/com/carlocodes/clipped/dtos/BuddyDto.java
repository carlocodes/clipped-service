package com.carlocodes.clipped.dtos;

import com.carlocodes.clipped.entities.User;

import java.time.LocalDateTime;

public class BuddyDto {
    private long id;
    private User sender;
    private User receiver;
    private Boolean accepted;
    private LocalDateTime createdDateTime;
    private LocalDateTime acceptedDateTime;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public User getReceiver() {
        return receiver;
    }

    public void setReceiver(User receiver) {
        this.receiver = receiver;
    }

    public Boolean getAccepted() {
        return accepted;
    }

    public void setAccepted(Boolean accepted) {
        this.accepted = accepted;
    }

    public LocalDateTime getCreatedDateTime() {
        return createdDateTime;
    }

    public void setCreatedDateTime(LocalDateTime createdDateTime) {
        this.createdDateTime = createdDateTime;
    }

    public LocalDateTime getAcceptedDateTime() {
        return acceptedDateTime;
    }

    public void setAcceptedDateTime(LocalDateTime acceptedDateTime) {
        this.acceptedDateTime = acceptedDateTime;
    }

    @Override
    public String toString() {
        return "BuddyDto{" +
                "id=" + id +
                ", sender=" + sender +
                ", receiver=" + receiver +
                ", accepted=" + accepted +
                ", createdDateTime=" + createdDateTime +
                ", acceptedDateTime=" + acceptedDateTime +
                '}';
    }
}

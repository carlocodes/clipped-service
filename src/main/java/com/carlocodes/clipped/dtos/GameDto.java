package com.carlocodes.clipped.dtos;

import java.time.LocalDateTime;

public class GameDto {
    private Integer id;
    private String name;
    private LocalDateTime createdDateTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getCreatedDateTime() {
        return createdDateTime;
    }

    public void setCreatedDateTime(LocalDateTime createdDateTime) {
        this.createdDateTime = createdDateTime;
    }

    @Override
    public String toString() {
        return "GameDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", createdDateTime=" + createdDateTime +
                '}';
    }
}

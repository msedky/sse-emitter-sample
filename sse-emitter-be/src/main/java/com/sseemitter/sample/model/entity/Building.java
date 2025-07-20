package com.sseemitter.sample.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "buildings")
@Getter
@Setter
public class Building {
    @Id
    private UUID id;
    @Column(name = "name_en", nullable = false, unique = true)
    private String nameEn;
    @Column(name = "name_ar", nullable = false, unique = true)
    private String nameAr;
    private LocalDateTime createdAt;

    @PrePersist
    public void prePersist() {
        if (id == null) id = UUID.randomUUID();
        if (createdAt == null) createdAt = LocalDateTime.now();
    }
}
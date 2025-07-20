package com.sseemitter.sample.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
public class BuildingDTO {
    private UUID id;
    private String nameEn;
    private String nameAr;
    private LocalDateTime createdAt;
}

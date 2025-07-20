package com.sseemitter.sample.controller;

import com.sseemitter.sample.model.dto.BuildingDTO;
import com.sseemitter.sample.model.request.BuildingRequest;
import com.sseemitter.sample.service.BuildingService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/buildings")
@RequiredArgsConstructor
public class BuildingController {
    private final BuildingService buildingService;

    @PostMapping
    public BuildingDTO create(@RequestBody BuildingRequest request) {
        return buildingService.create(request);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id) {
        buildingService.delete(id);
    }

    @GetMapping
    public List<BuildingDTO> getAll() {
        return buildingService.getAll();
    }

    @GetMapping("/count")
    public long countBuildings() {
        return buildingService.count();
    }
}

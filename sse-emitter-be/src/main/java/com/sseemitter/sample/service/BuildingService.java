package com.sseemitter.sample.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sseemitter.sample.model.dto.BuildingDTO;
import com.sseemitter.sample.model.entity.Building;
import com.sseemitter.sample.model.request.BuildingRequest;
import com.sseemitter.sample.repository.BuildingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BuildingService {
    private final BuildingRepository repository;
    private final ObjectMapper objectMapper;

    @Transactional
    public BuildingDTO create(BuildingRequest request) {
        Building building = objectMapper.convertValue(request, Building.class);
        building = repository.save(building);
        BuildingDTO buildingDTO = objectMapper.convertValue(building, BuildingDTO.class);
        return buildingDTO;
    }

    @Transactional
    public BuildingDTO update(UUID id, BuildingRequest request) {
        Building building = objectMapper.convertValue(request, Building.class);
        building.setId(id);
        building = repository.save(building);
        BuildingDTO buildingDTO = objectMapper.convertValue(building, BuildingDTO.class);
        return buildingDTO;
    }

    @Transactional
    public void delete(UUID id) {
        repository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public List<BuildingDTO> getAll() {
        List<Building> buildings = repository.findAll();
        if (!CollectionUtils.isEmpty(buildings)) {
            return buildings.stream().map(e -> objectMapper.convertValue(e, BuildingDTO.class)).collect(Collectors.toList());
        }
        return null;
    }

    @Transactional(readOnly = true)
    public long count() {
        return repository.count();
    }


}
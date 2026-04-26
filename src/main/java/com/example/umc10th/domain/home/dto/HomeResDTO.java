package com.example.umc10th.domain.home.dto;

import com.example.umc10th.global.dto.PageResDTO.PageInfoDTO;
import lombok.Builder;

import java.time.LocalDate;
import java.util.List;

public class HomeResDTO {

    @Builder
    public record HomeViewDTO(
            RegionDTO region,
            PageInfoDTO pageInfo,
            List<HomeMissionDTO> missions
    ) {}

    @Builder
    public record RegionDTO(
            Long regionId,
            String regionName,
            Integer completedMissionCount
    ) {}

    @Builder
    public record HomeMissionDTO(
            Long missionId,
            Long storeId,
            String storeName,
            String storeCategory,
            Integer missionPoint,
            String missionCondition,
            LocalDate missionDeadline,
            String deadlineLabel)
    {}
}

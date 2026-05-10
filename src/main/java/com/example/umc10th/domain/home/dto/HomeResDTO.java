package com.example.umc10th.domain.home.dto;

import com.example.umc10th.global.dto.PageResDTO;
import lombok.Builder;

import java.time.LocalDate;

public class HomeResDTO {

    @Builder
    public record HomeViewDTO(
            RegionDTO region,
            PageResDTO<HomeMissionDTO> missionPage
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

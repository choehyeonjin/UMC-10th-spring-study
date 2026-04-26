package com.example.umc10th.domain.mission.dto;

import com.example.umc10th.global.dto.PageResDTO.PageInfoDTO;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;

public class MissionResDTO {

    // 미션 목록 조회
    @Builder
    public record MissionListDTO(
            PageInfoDTO pageInfo,
            List<MissionDetailDTO> missions
    ) {}

    @Builder
    public record MissionDetailDTO(
            Long memberMissionId,
            Long missionId,
            Long storeId,
            String status,
            String storeName,
            Integer missionPoint,
            String missionCondition,
            Boolean reviewWritten)
    {}

    // 미션 도전
    @Builder
    public record MissionChallengeDTO(
            Long memberMissionId,
            Long missionId,
            String status,
            LocalDateTime createdAt)
    {}

    // 미션 성공
    @Builder
    public record MissionSuccessDTO(
            Long memberMissionId,
            String status,
            LocalDateTime updatedAt
    ) {}
}

package com.example.umc10th.domain.home.converter;

import com.example.umc10th.domain.home.dto.HomeResDTO;
import com.example.umc10th.domain.mission.entity.Region;
import com.example.umc10th.domain.mission.entity.Mission;
import com.example.umc10th.global.dto.PageResDTO;
import com.example.umc10th.global.dto.PageResDTO.PageInfoDTO;
import org.springframework.data.domain.Page;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

public class HomeConverter {

    public static HomeResDTO.HomeViewDTO toHomeViewDTO(Region region, long completedCount, Page<Mission> missionPage) {
        // 엔티티 리스트 -> DTO 리스트 변환
        List<HomeResDTO.HomeMissionDTO> missionDTOList = missionPage.getContent().stream()
                .map(HomeConverter::toHomeMissionDTO)
                .collect(Collectors.toList());

        // 공통 페이징 응답 객체 조립
        PageResDTO<HomeResDTO.HomeMissionDTO> missionPageResponse = PageResDTO.<HomeResDTO.HomeMissionDTO>builder()
                .data(missionDTOList)
                .pageInfo(PageInfoDTO.builder()
                        .page(missionPage.getNumber())
                        .size(missionPage.getSize())
                        .totalElements(missionPage.getTotalElements())
                        .totalPages(missionPage.getTotalPages())
                        .hasNext(missionPage.hasNext())
                        .build())
                .build();

        // 최종 HomeViewDTO 반환
        return HomeResDTO.HomeViewDTO.builder()
                .region(HomeResDTO.RegionDTO.builder()
                        .regionId(region.getId())
                        .regionName(region.getName())
                        .completedMissionCount((int) completedCount)
                        .build())
                .missionPage(missionPageResponse)
                .build();
    }

    private static HomeResDTO.HomeMissionDTO toHomeMissionDTO(Mission mission) {
        LocalDate today = LocalDate.now();
        long daysBetween = ChronoUnit.DAYS.between(today, mission.getDeadline());

        String label = "D-" + daysBetween;

        return HomeResDTO.HomeMissionDTO.builder()
                .missionId(mission.getId())
                .storeId(mission.getStore().getId())
                .storeName(mission.getStore().getName())
                .storeCategory(mission.getStore().getCategory())
                .missionPoint(mission.getPoint())
                .missionCondition(mission.getMissionCondition())
                .missionDeadline(mission.getDeadline())
                .deadlineLabel(label)
                .build();
    }
}
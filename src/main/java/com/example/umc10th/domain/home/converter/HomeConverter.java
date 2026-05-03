package com.example.umc10th.domain.home.converter;

import com.example.umc10th.domain.home.dto.HomeResDTO;
import com.example.umc10th.domain.mission.entity.Region;
import com.example.umc10th.domain.mission.entity.Mission;
import com.example.umc10th.global.dto.PageResDTO.PageInfoDTO;
import org.springframework.data.domain.Page;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

public class HomeConverter {

    public static HomeResDTO.HomeViewDTO toHomeViewDTO(Region region, long completedCount, Page<Mission> missionPage) {
        List<HomeResDTO.HomeMissionDTO> missionDTOList = missionPage.getContent().stream()
                .map(HomeConverter::toHomeMissionDTO)
                .collect(Collectors.toList());

        return HomeResDTO.HomeViewDTO.builder()
                .region(HomeResDTO.RegionDTO.builder()
                        .regionId(region.getId())
                        .regionName(region.getName())
                        .completedMissionCount((int) completedCount)
                        .build())
                .pageInfo(PageInfoDTO.builder()
                        .page(missionPage.getNumber())
                        .size(missionPage.getSize())
                        .totalElements(missionPage.getTotalElements())
                        .totalPages(missionPage.getTotalPages())
                        .hasNext(missionPage.hasNext())
                        .build())
                .missions(missionDTOList)
                .build();
    }

    private static HomeResDTO.HomeMissionDTO toHomeMissionDTO(Mission mission) {
        LocalDate today = LocalDate.now();

        long daysBetween = ChronoUnit.DAYS.between(today, mission.getDeadline());
        String label = daysBetween == 0 ? "D-Day" : (daysBetween > 0 ? "D-" + daysBetween : "기한 만료");

        return HomeResDTO.HomeMissionDTO.builder()
                .missionId(mission.getId())
                .storeId(mission.getStore().getId())
                .storeName(mission.getStore().getName())
                .storeCategory(mission.getStore().getCategory()) // 엔티티 타입(String) 반영
                .missionPoint(mission.getPoint())
                .missionCondition(mission.getMissionCondition())
                .missionDeadline(mission.getDeadline()) // 엔티티 필드명(deadline) 반영
                .deadlineLabel(label)
                .build();
    }
}
package com.example.umc10th.domain.mission.converter;

import com.example.umc10th.domain.mission.dto.MissionResDTO;
import com.example.umc10th.domain.mission.entity.mapping.MemberMission;
import com.example.umc10th.global.dto.PageResDTO;
import com.example.umc10th.global.dto.PageResDTO.PageInfoDTO;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class MissionConverter {

    public static PageResDTO<MissionResDTO.MissionDetailDTO> toMissionPageResponse(
            Page<MemberMission> missionPage, List<Boolean> reviewWrittenList) {

        List<MissionResDTO.MissionDetailDTO> missionDetailList = IntStream.range(0, missionPage.getContent().size())
                .mapToObj(i -> {
                    MemberMission memberMission = missionPage.getContent().get(i);
                    Boolean reviewWritten = reviewWrittenList.get(i);
                    return toMissionDetailDTO(memberMission, reviewWritten);
                })
                .collect(Collectors.toList());

        return PageResDTO.<MissionResDTO.MissionDetailDTO>builder()
                .content(missionDetailList)
                .pageInfo(PageInfoDTO.builder()
                        .page(missionPage.getNumber())
                        .size(missionPage.getSize())
                        .totalElements(missionPage.getTotalElements())
                        .totalPages(missionPage.getTotalPages())
                        .hasNext(missionPage.hasNext())
                        .build())
                .build();
    }

    private static MissionResDTO.MissionDetailDTO toMissionDetailDTO(MemberMission memberMission, Boolean reviewWritten) {
        return MissionResDTO.MissionDetailDTO.builder()
                .memberMissionId(memberMission.getId())
                .missionId(memberMission.getMission().getId())
                .storeId(memberMission.getMission().getStore().getId())
                .status(memberMission.getStatus().name())
                .storeName(memberMission.getMission().getStore().getName())
                .missionPoint(memberMission.getMission().getPoint())
                .missionCondition(memberMission.getMission().getMissionCondition())
                .reviewWritten(reviewWritten)
                .build();
    }
}
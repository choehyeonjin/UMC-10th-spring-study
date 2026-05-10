package com.example.umc10th.domain.mission.dto;

import lombok.Getter;

public class MissionReqDTO {

    // 미션 목록 조회
    @Getter
    public static class GetMyMissionsReqDTO {
        private Long memberId;
    }

}
package com.example.umc10th.domain.mission.controller;

import com.example.umc10th.domain.home.exception.code.HomeSuccessCode;
import com.example.umc10th.domain.mission.dto.MissionReqDTO;
import com.example.umc10th.domain.mission.dto.MissionResDTO;
import com.example.umc10th.domain.mission.exception.code.MissionSuccessCode;
import com.example.umc10th.domain.mission.service.MissionService;
import com.example.umc10th.global.apiPayload.ApiResponse;
import com.example.umc10th.global.apiPayload.code.BaseSuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class MissionController {

    // private final MissionService missionService;

    // 미션 목록 조회
    @GetMapping("/v1/missions")
    public ApiResponse<MissionResDTO.MissionListDTO> getMissions(
            @RequestParam String status,
            @RequestParam(defaultValue = "0") Integer page
    ) {
        BaseSuccessCode code = MissionSuccessCode.OK;
        return ApiResponse.onSuccess(
                code,
                null // missionService
        );
    }

    // 미션 도전
    @PostMapping("/v1/missions/{missionId}/challenge")
    public ApiResponse<MissionResDTO.MissionChallengeDTO> challengeMission(
            @PathVariable Long missionId
    ) {
        BaseSuccessCode code = MissionSuccessCode.CHALLENGE_OK;
        return ApiResponse.onSuccess(
                code,
                null // missionService
        );
    }

    // 미션 성공
    @PatchMapping("/v1/member-missions/{memberMissionId}/success")
    public ApiResponse<MissionResDTO.MissionSuccessDTO> completeMission(
            @PathVariable Long memberMissionId
    ) {
        BaseSuccessCode code = MissionSuccessCode.SUCCESS_OK;
        return ApiResponse.onSuccess(
                code,
                null); // missionService
    }
}
package com.example.umc10th.domain.mission.controller;

import com.example.umc10th.domain.home.exception.code.HomeSuccessCode;
import com.example.umc10th.domain.mission.dto.MissionReqDTO;
import com.example.umc10th.domain.mission.dto.MissionResDTO;
import com.example.umc10th.domain.mission.exception.code.MissionSuccessCode;
import com.example.umc10th.domain.mission.service.MissionService;
import com.example.umc10th.global.apiPayload.ApiResponse;
import com.example.umc10th.global.apiPayload.code.BaseSuccessCode;
import com.example.umc10th.global.dto.PageResDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class MissionController {

    private final MissionService missionService;

    // 미션 목록 조회
    @GetMapping("/missions")
    public ApiResponse<PageResDTO<MissionResDTO.MissionDetailDTO>> getMissions(
            @RequestParam String status,
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "3") Integer size
    ) {
        Long memberId = 1L; // TODO: 인증 연동

        PageResDTO<MissionResDTO.MissionDetailDTO> resultDTO = missionService.getMyMissions(memberId, status, page, size);

        return ApiResponse.onSuccess(
                MissionSuccessCode.OK,
                resultDTO
        );
    }

    // 미션 도전
    @PostMapping("/missions/{missionId}/challenge")
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
    @PatchMapping("/member-missions/{memberMissionId}/success")
    public ApiResponse<MissionResDTO.MissionSuccessDTO> completeMission(
            @PathVariable Long memberMissionId
    ) {
        BaseSuccessCode code = MissionSuccessCode.SUCCESS_OK;
        return ApiResponse.onSuccess(
                code,
                null); // missionService
    }
}
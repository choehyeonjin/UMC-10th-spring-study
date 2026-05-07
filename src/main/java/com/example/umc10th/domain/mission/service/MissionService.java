package com.example.umc10th.domain.mission.service;

import com.example.umc10th.domain.mission.dto.MissionResDTO;

public interface MissionService {
    MissionResDTO.MissionListDTO getMyMissions(Long memberId, String status, Integer page, Integer size);
}

package com.example.umc10th.domain.mission.service;

import com.example.umc10th.domain.mission.dto.MissionResDTO;
import com.example.umc10th.global.dto.PageResDTO;

public interface MissionService {
    PageResDTO<MissionResDTO.MissionDetailDTO> getMyMissions(Long memberId, String status, Integer page, Integer size);
}

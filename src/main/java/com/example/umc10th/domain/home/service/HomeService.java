package com.example.umc10th.domain.home.service;

import com.example.umc10th.domain.home.dto.HomeResDTO;

public interface HomeService {
    HomeResDTO.HomeViewDTO getHomeView(Long memberId, Long regionId, Integer page, Integer size);
}

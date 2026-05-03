package com.example.umc10th.domain.home.controller;

import com.example.umc10th.domain.home.dto.HomeResDTO;
import com.example.umc10th.domain.home.exception.code.HomeSuccessCode;
import com.example.umc10th.domain.home.service.HomeService;
import com.example.umc10th.global.apiPayload.ApiResponse;
import com.example.umc10th.global.apiPayload.code.BaseSuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class HomeController {

    private final HomeService homeService;

    // 홈 화면 조회
    @GetMapping("/home")
    public ApiResponse<HomeResDTO.HomeViewDTO> getHomeView(
            @RequestParam Long regionId,
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "3") Integer size
    ) {
        Long memberId = 1L; // TODO: 인증 연동

        HomeResDTO.HomeViewDTO resultDTO = homeService.getHomeView(memberId, regionId, page, size);

        BaseSuccessCode code = HomeSuccessCode.OK;
        return ApiResponse.onSuccess(
                code,
                resultDTO);
    }
}
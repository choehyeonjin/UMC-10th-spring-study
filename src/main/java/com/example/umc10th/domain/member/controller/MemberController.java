package com.example.umc10th.domain.member.controller;

import com.example.umc10th.domain.member.dto.MemberReqDTO;
import com.example.umc10th.domain.member.dto.MemberResDTO;
import com.example.umc10th.domain.member.exception.code.MemberSuccessCode;
import com.example.umc10th.domain.member.service.MemberService;
import com.example.umc10th.global.apiPayload.ApiResponse;
import com.example.umc10th.global.apiPayload.code.BaseSuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    // 회원가입
    @PostMapping("/auth/signup")
    public ApiResponse<MemberResDTO.JoinResultDTO> signUp(
            @RequestBody MemberReqDTO.JoinDTO request
            ) {
        BaseSuccessCode code = MemberSuccessCode.SIGNUP_CREATED;
        return ApiResponse.onSuccess(
                code,
                null); // memberService
    }

    // 마이페이지 조회
    @GetMapping("/members/me")
    public ApiResponse<MemberResDTO.MyPageResultDTO> getMyPage(
    ) {
        Long memberId = 1L; // TODO: 인증 연동

        MemberResDTO.MyPageResultDTO resultDTO = memberService.getMyPage(memberId);

        BaseSuccessCode code = MemberSuccessCode.MYPAGE_OK;
        return ApiResponse.onSuccess(
                code,
                resultDTO);
    }
}

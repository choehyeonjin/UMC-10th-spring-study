package com.example.umc10th.domain.member.controller;

import com.example.umc10th.domain.member.dto.MemberReqDTO;
import com.example.umc10th.domain.member.dto.MemberResDTO;
import com.example.umc10th.domain.member.exception.code.MemberSuccessCode;
import com.example.umc10th.domain.member.service.MemberService;
import com.example.umc10th.global.apiPayload.ApiResponse;
import com.example.umc10th.global.apiPayload.code.BaseSuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class MemberController {

    // private final MemberService memberService;

    // 회원가입
    @PostMapping("/signup")
    public ApiResponse<MemberResDTO.JoinResultDTO> signUp(
            @RequestBody MemberReqDTO.JoinDTO request
            ) {
        BaseSuccessCode code = MemberSuccessCode.SIGNUP_OK;
        return ApiResponse.onSuccess(
                code,
                null); // memberService
    }
}

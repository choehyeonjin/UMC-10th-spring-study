package com.example.umc10th.global.security.handler;

import com.example.umc10th.domain.member.entity.Member;
import com.example.umc10th.domain.member.exception.code.MemberSuccessCode;
import com.example.umc10th.global.apiPayload.ApiResponse;
import com.example.umc10th.global.apiPayload.code.BaseSuccessCode;
import com.example.umc10th.global.security.entity.AuthMember;
import com.example.umc10th.global.security.entity.OAuthMember;
import com.example.umc10th.global.security.util.JwtUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
public class OAuthSuccessHandler implements AuthenticationSuccessHandler {

    private final JwtUtil jwtUtil;

    @Override
    public void onAuthenticationSuccess(
            HttpServletRequest request,
            HttpServletResponse response,
            Authentication authentication
    ) throws IOException, ServletException {

        ObjectMapper objectMapper = new ObjectMapper();
        response.setContentType("application/json;charset=UTF-8");

        // 1. 기존 유저 정보 꺼내기
        OAuthMember oAuthMember = (OAuthMember) authentication.getPrincipal();
        Member member = oAuthMember.getMember();

        // 2. 분기 처리 후 JSON 조립
        if (member.getId() != null) {
            // 기존 회원 -> 로그인 성공 처리 및 JWT 발급
            response.setStatus(HttpStatus.OK.value());
            String accessToken = jwtUtil.createAccessToken(new AuthMember(member));

            // 데이터 조립
            Map<String, Object> data = new HashMap<>();
            data.put("status", "LOGIN_SUCCESS");
            data.put("accessToken", accessToken);

            BaseSuccessCode successCode = MemberSuccessCode.LOGIN_OK;
            ApiResponse<Map<String, Object>> responseBody = ApiResponse.onSuccess(successCode, data);

            objectMapper.writeValue(response.getOutputStream(), responseBody);

        } else {
            // 신규 회원 -> 회원가입이 필요함을 알리고 소셜 정보를 JSON으로 반환
            response.setStatus(HttpStatus.ACCEPTED.value());

            Map<String, Object> data = new HashMap<>();
            data.put("status", "REQUIRE_SIGNUP");
            data.put("socialType", member.getSocialType().toString());
            data.put("socialUid", member.getSocialUid());
            data.put("email", member.getEmail());
            data.put("name", member.getName());

            BaseSuccessCode successCode = MemberSuccessCode.SOCIAL_AUTH_OK;
            ApiResponse<Map<String, Object>> responseBody = ApiResponse.onSuccess(successCode, data);

            objectMapper.writeValue(response.getOutputStream(), responseBody);
        }
    }
}
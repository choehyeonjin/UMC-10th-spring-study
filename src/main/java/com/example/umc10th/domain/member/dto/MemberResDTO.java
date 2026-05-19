package com.example.umc10th.domain.member.dto;

import lombok.Builder;

import java.time.LocalDateTime;

public class MemberResDTO {

    // 회원가입
    @Builder
    public record JoinResultDTO(
            Long memberId,
            LocalDateTime createdAt,
            String accessToken
    ) {}

    // 마이페이지 조회
    @Builder
    public record MyPageResultDTO(
            String profileImageUrl,
            String nickname,
            String email,
            String phoneNumber,
            Boolean isPhoneVerified,
            Integer point
    ) {}
}

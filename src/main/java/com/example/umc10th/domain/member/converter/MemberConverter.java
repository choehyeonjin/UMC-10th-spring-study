package com.example.umc10th.domain.member.converter;

import com.example.umc10th.domain.member.dto.MemberResDTO;
import com.example.umc10th.domain.member.entity.Member;

public class MemberConverter {

    public static MemberResDTO.MyPageResultDTO toMyPageResultDTO(Member member) {
        return MemberResDTO.MyPageResultDTO.builder()
                .profileImageUrl(member.getProfileImage() != null ? member.getProfileImage().getImageUrl() : null)
                .nickname(member.getNickname())
                .email(member.getEmail())
                .phoneNumber(member.getPhone())
                .isPhoneVerified(member.isPhoneVerified())
                .point(member.getPoint())
                .build();
    }
}
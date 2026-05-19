package com.example.umc10th.domain.member.converter;

import com.example.umc10th.domain.member.dto.MemberReqDTO;
import com.example.umc10th.domain.member.dto.MemberResDTO;
import com.example.umc10th.domain.member.entity.Food;
import com.example.umc10th.domain.member.entity.Member;
import com.example.umc10th.domain.member.entity.Term;
import com.example.umc10th.domain.member.entity.mapping.MemberFood;
import com.example.umc10th.domain.member.entity.mapping.MemberTerm;
import com.example.umc10th.domain.member.enums.Gender;
import com.example.umc10th.domain.member.enums.MemberType;
import com.example.umc10th.domain.member.enums.SocialType;

public class MemberConverter {

    // 요청 DTO -> Member 엔티티
    public static Member toMember(MemberReqDTO.JoinDTO request) {
        return Member.builder()
                .socialType(SocialType.valueOf(request.socialType().toUpperCase()))
                .socialUid(request.socialUid())
                .email(request.email())
                .name(request.name())
                .gender(Gender.valueOf(request.gender()))
                .birthdate(request.birthdate())
                .address(request.address())
                .phoneVerified(false)
                .point(0)
                .memberType(MemberType.USER)
                .build();
    }

    // Member 엔티티 -> 회원가입 응답 DTO
    public static MemberResDTO.JoinResultDTO toJoinResultDTO(Member member, String accessToken) {
        return MemberResDTO.JoinResultDTO.builder()
                .memberId(member.getId())
                .createdAt(member.getCreatedAt())
                .accessToken(accessToken)
                .build();
    }

    public static MemberTerm toMemberTerm(Member member, Term term) {
        return MemberTerm.builder()
                .member(member)
                .term(term)
                .build();
    }

    public static MemberFood toMemberFood(Member member, Food food) {
        return MemberFood.builder()
                .member(member)
                .food(food)
                .build();
    }

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
package com.example.umc10th.domain.member.service;

import com.example.umc10th.domain.member.converter.MemberConverter;
import com.example.umc10th.domain.member.dto.MemberReqDTO;
import com.example.umc10th.domain.member.dto.MemberResDTO;
import com.example.umc10th.domain.member.entity.Food;
import com.example.umc10th.domain.member.entity.Member;
import com.example.umc10th.domain.member.entity.Term;
import com.example.umc10th.domain.member.entity.mapping.MemberFood;
import com.example.umc10th.domain.member.entity.mapping.MemberTerm;
import com.example.umc10th.domain.member.enums.SocialType;
import com.example.umc10th.domain.member.exception.MemberException;
import com.example.umc10th.domain.member.exception.code.MemberErrorCode;
import com.example.umc10th.domain.member.repository.*;
import com.example.umc10th.global.security.entity.AuthMember;
import com.example.umc10th.global.security.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final TermRepository termRepository;
    private final FoodRepository foodRepository;
    private final MemberTermRepository memberTermRepository;
    private final MemberFoodRepository memberFoodRepository;
    private final JwtUtil jwtUtil;

    @Override
    @Transactional
    public MemberResDTO.JoinResultDTO signUp(MemberReqDTO.JoinDTO request) {

        // 필수 약관 검증
        List<Long> requiredTermIds = List.of(1L, 2L, 3L);
        if (request.termIds() == null || !new HashSet<>(request.termIds()).containsAll(requiredTermIds)) {
            throw new MemberException(MemberErrorCode.TERM_NOT_AGREED);
        }

        // 이미 가입된 소셜 계정인지 검증
        if (memberRepository.findBySocialTypeAndSocialUid(
                SocialType.valueOf(request.socialType()), request.socialUid()).isPresent()) {
            throw new MemberException(MemberErrorCode.MEMBER_ALREADY_EXISTS);
        }

        // 1. Member 엔티티 생성 및 저장 (비밀번호 암호화 로직 삭제)
        Member newMember = MemberConverter.toMember(request);
        Member savedMember = memberRepository.save(newMember);

        // MemberTerm 매핑 및 저장
        List<MemberTerm> memberTerms = request.termIds().stream()
                .map(termId -> {
                    Term term = termRepository.findById(termId)
                            .orElseThrow(() -> new MemberException(MemberErrorCode.TERM_NOT_FOUND));
                    return MemberConverter.toMemberTerm(savedMember, term);
                }).collect(Collectors.toList());
        memberTermRepository.saveAll(memberTerms);

        // MemberFood 매핑 및 저장
        if (request.preferredFoodIds() != null && !request.preferredFoodIds().isEmpty()) {
            List<MemberFood> memberFoods = request.preferredFoodIds().stream()
                    .map(foodId -> {
                        Food food = foodRepository.findById(foodId)
                                .orElseThrow(() -> new MemberException(MemberErrorCode.FOOD_TYPE_NOT_FOUND));
                        return MemberConverter.toMemberFood(savedMember, food);
                    }).collect(Collectors.toList());
            memberFoodRepository.saveAll(memberFoods);
        }

        // JWT Access Token 발급
        String accessToken = jwtUtil.createAccessToken(new AuthMember(savedMember));

        return MemberConverter.toJoinResultDTO(savedMember, accessToken);
    }

    @Override
    public MemberResDTO.MyPageResultDTO getMyPage(
            AuthMember member
    ) {

        return MemberConverter.toMyPageResultDTO(member.getMember());
    }
}
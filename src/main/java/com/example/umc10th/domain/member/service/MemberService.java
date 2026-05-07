package com.example.umc10th.domain.member.service;

import com.example.umc10th.domain.member.dto.MemberResDTO;

public interface MemberService {
    MemberResDTO.MyPageResultDTO getMyPage(Long memberId);
}

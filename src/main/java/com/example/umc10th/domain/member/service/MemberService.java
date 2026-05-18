package com.example.umc10th.domain.member.service;

import com.example.umc10th.domain.member.dto.MemberReqDTO;
import com.example.umc10th.domain.member.dto.MemberResDTO;

public interface MemberService {
    MemberResDTO.JoinResultDTO signUp(MemberReqDTO.JoinDTO request);
    MemberResDTO.MyPageResultDTO getMyPage(Long memberId);
}

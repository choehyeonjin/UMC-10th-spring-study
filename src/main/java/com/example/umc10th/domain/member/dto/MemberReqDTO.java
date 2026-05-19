package com.example.umc10th.domain.member.dto;

import java.time.LocalDate;
import java.util.List;

public class MemberReqDTO {

    // 회원가입
    public record JoinDTO(
            String socialType,
            String socialUid,
            String email,
            String name,
            String gender,
            LocalDate birthdate,
            String address,
            List<Long> termIds,
            List<Long> preferredFoodIds
    ) {}
}

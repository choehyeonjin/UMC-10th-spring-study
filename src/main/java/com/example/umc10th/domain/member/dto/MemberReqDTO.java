package com.example.umc10th.domain.member.dto;

import java.time.LocalDate;
import java.util.List;

public class MemberReqDTO {

    // 회원가입
    public record JoinDTO(
            List<Long> termIds,
            String name,
            String gender,
            LocalDate birthdate,
            String address,
            List<Long> preferredFoodIds
    ) {}
}

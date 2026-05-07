package com.example.umc10th.domain.member.exception.code;

import com.example.umc10th.global.apiPayload.code.BaseErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum MemberErrorCode implements BaseErrorCode {

    MEMBER_NOT_FOUND(HttpStatus.NOT_FOUND,
            "MEMBER404_1",
            "존재하지 않는 회원입니다.")
    ,
    INVALID_MEMBER_STATUS(HttpStatus.BAD_REQUEST,
            "MEMBER400_1",
            "비활성화되거나 탈퇴한 회원입니다.")
    ;

    private final HttpStatus status;
    private final String code;
    private final String message;
}
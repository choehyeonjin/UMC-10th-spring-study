package com.example.umc10th.domain.home.exception.code;

import com.example.umc10th.global.apiPayload.code.BaseErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum HomeErrorCode implements BaseErrorCode {

    MEMBER_NOT_FOUND(HttpStatus.NOT_FOUND,
            "HOME404_1",
            "존재하지 않는 회원입니다.")
    ,
    REGION_NOT_FOUND(HttpStatus.NOT_FOUND,
            "HOME404_2",
            "존재하지 않는 지역입니다.")
    ;

    private final HttpStatus status;
    private final String code;
    private final String message;
}
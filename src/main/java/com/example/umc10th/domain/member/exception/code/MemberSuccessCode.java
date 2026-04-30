package com.example.umc10th.domain.member.exception.code;

import com.example.umc10th.global.apiPayload.code.BaseSuccessCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum MemberSuccessCode implements BaseSuccessCode {

    SIGNUP_OK(HttpStatus.CREATED,
            "MEMBER201_1",
            "회원가입에 성공했습니다."),
    ;

    private final HttpStatus status;
    private final String code;
    private final String message;
}

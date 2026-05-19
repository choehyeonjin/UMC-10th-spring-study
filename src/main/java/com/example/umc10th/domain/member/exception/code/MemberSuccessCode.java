package com.example.umc10th.domain.member.exception.code;

import com.example.umc10th.global.apiPayload.code.BaseSuccessCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum MemberSuccessCode implements BaseSuccessCode {

    MYPAGE_OK(HttpStatus.OK,
            "MEMBER200_1",
            "마이페이지 조회에 성공했습니다."),

    SIGNUP_CREATED(HttpStatus.CREATED,
            "MEMBER201_1",
            "회원가입에 성공했습니다."),

    LOGIN_OK(HttpStatus.OK,
            "MEMBER200_2",
            "소셜 로그인에 성공했습니다."),

    SOCIAL_AUTH_OK(HttpStatus.ACCEPTED,
            "MEMBER202_1",
            "소셜 인증에 성공했습니다. 추가 회원가입이 필요합니다.")
    ;

    private final HttpStatus status;
    private final String code;
    private final String message;
}
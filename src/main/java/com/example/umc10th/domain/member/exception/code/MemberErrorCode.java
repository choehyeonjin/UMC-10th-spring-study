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
    ,
    NOT_SUPPORT_SOCIAL_PROVIDER(HttpStatus.BAD_REQUEST,
            "MEMBER400_2",
            "지원하지 않는 소셜 로그인 제공자입니다.")
    ,
    MEMBER_ALREADY_EXISTS(HttpStatus.BAD_REQUEST,
            "MEMBER400_3",
            "이미 가입된 회원입니다.")
    ,
    // 약관 관련 에러
    TERM_NOT_AGREED(HttpStatus.BAD_REQUEST,
            "MEMBER400_4",
            "필수 약관에 모두 동의해야 합니다.")
    ,
    TERM_NOT_FOUND(HttpStatus.NOT_FOUND,
            "MEMBER404_2",
            "존재하지 않는 약관입니다.")
    ,
    // 선호 음식 관련 에러
    FOOD_TYPE_NOT_FOUND(HttpStatus.NOT_FOUND,
            "MEMBER404_3",
            "존재하지 않는 음식 종류입니다.")
    ;

    private final HttpStatus status;
    private final String code;
    private final String message;
}
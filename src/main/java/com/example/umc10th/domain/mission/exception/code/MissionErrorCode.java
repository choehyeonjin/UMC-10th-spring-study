package com.example.umc10th.domain.mission.exception.code;

import com.example.umc10th.global.apiPayload.code.BaseErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum MissionErrorCode implements BaseErrorCode {

    MEMBER_NOT_FOUND(HttpStatus.NOT_FOUND,
            "MISSION404_1",
            "존재하지 않는 회원입니다.")
    ,
    INVALID_MISSION_STATUS(HttpStatus.BAD_REQUEST,
            "MISSION400_1",
            "유효하지 않은 미션 상태입니다.")
    ;

    private final HttpStatus status;
    private final String code;
    private final String message;
}
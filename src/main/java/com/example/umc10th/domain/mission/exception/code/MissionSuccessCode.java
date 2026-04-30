package com.example.umc10th.domain.mission.exception.code;

import com.example.umc10th.global.apiPayload.code.BaseSuccessCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum MissionSuccessCode implements BaseSuccessCode {

    OK(HttpStatus.OK,
            "MISSION200_1",
            "미션 목록 조회에 성공했습니다."),

    SUCCESS_OK(HttpStatus.OK,
            "MISSION200_2",
            "미션 성공 처리에 성공했습니다."),

    CHALLENGE_OK(HttpStatus.CREATED,
            "MISSION201_1",
            "미션 도전에 성공했습니다."),
    ;

    private final HttpStatus status;
    private final String code;
    private final String message;
}

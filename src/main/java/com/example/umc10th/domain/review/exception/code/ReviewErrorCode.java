package com.example.umc10th.domain.review.exception.code;

import com.example.umc10th.global.apiPayload.code.BaseErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ReviewErrorCode implements BaseErrorCode {

    INVALID_SORT_TYPE(HttpStatus.BAD_REQUEST,
            "REVIEW400_1",
            "유효하지 않은 정렬 기준입니다.")
    ,
    MEMBER_NOT_FOUND(HttpStatus.NOT_FOUND,
            "REVIEW404_1",
            "존재하지 않는 회원입니다.")
    ,
    STORE_NOT_FOUND(HttpStatus.NOT_FOUND,
            "REVIEW404_2",
            "존재하지 않는 가게입니다.")
    ,
    IMAGE_NOT_FOUND(HttpStatus.NOT_FOUND,
            "REVIEW404_3",
            "첨부하려는 이미지를 찾을 수 없습니다.")
    ;

    private final HttpStatus status;
    private final String code;
    private final String message;
}

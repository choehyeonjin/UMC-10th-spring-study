package com.example.umc10th.domain.review.dto;

import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;

public class ReviewResDTO {

    // 리뷰 작성
    @Builder
    public record CreateReviewResultDTO(
            Long reviewId,
            LocalDateTime createdAt
    ) {}

    // 내 리뷰 목록
    @Builder
    public record ReviewCursorPageDTO(
            List<ReviewDetailDTO> data,
            ReviewCursorInfoDTO pageInfo
    ) {}

    @Builder
    public record ReviewDetailDTO(
            Long reviewId,
            Long storeId,
            String storeName,
            String nickname,
            Float rating,
            String content,
            LocalDateTime createdAt,
            String ownerReply,
            LocalDateTime ownerReplyCreatedAt
    ) {}

    // 커서 페이징 메타데이터
    @Builder
    public record ReviewCursorInfoDTO(
            Boolean hasNext,
            Long nextCursorId,
            Float nextCursorRating
    ) {}
}

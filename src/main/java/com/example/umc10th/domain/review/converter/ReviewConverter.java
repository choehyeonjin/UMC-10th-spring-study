package com.example.umc10th.domain.review.converter;

import com.example.umc10th.domain.review.dto.ReviewResDTO;
import com.example.umc10th.domain.review.entity.Review;
import org.springframework.data.domain.Slice;

import java.time.LocalDateTime;
import java.util.List;

public class ReviewConverter {

    // 리뷰 작성
    public static ReviewResDTO.CreateReviewResultDTO toCreateReviewResultDTO(Review review) {
        return ReviewResDTO.CreateReviewResultDTO.builder()
                .reviewId(review.getId())
                .createdAt(review.getCreatedAt())
                .build();
    }

    // 내 리뷰 조회
    public static ReviewResDTO.ReviewCursorPageDTO toReviewCursorPageDTO(Slice<Review> reviewSlice) {

        // 엔티티 리스트 -> DTO 리스트 변환
        List<ReviewResDTO.ReviewDetailDTO> reviewDetailList = reviewSlice.map(ReviewConverter::toReviewDetailDTO).toList();

        // 다음 커서 계산
        Long nextCursorId = null;
        Float nextCursorRating = null;

        if (!reviewDetailList.isEmpty() && reviewSlice.hasNext()) {
            ReviewResDTO.ReviewDetailDTO lastReview = reviewDetailList.get(reviewDetailList.size() - 1);
            nextCursorId = lastReview.reviewId();
            nextCursorRating = lastReview.rating();
        }

        // DTO 조립
        return ReviewResDTO.ReviewCursorPageDTO.builder()
                .data(reviewDetailList)
                .pageInfo(ReviewResDTO.ReviewCursorInfoDTO.builder()
                        .hasNext(reviewSlice.hasNext())
                        .nextCursorId(nextCursorId)
                        .nextCursorRating(nextCursorRating)
                        .build())
                .build();
    }

    private static ReviewResDTO.ReviewDetailDTO toReviewDetailDTO(Review review) {

        String ownerReplyContent = null;
        LocalDateTime ownerReplyCreatedAt = null;

        if (review.getReviewReply() != null) {
            ownerReplyContent = review.getReviewReply().getContent();
            ownerReplyCreatedAt = review.getReviewReply().getCreatedAt();
        }

        return ReviewResDTO.ReviewDetailDTO.builder()
                .reviewId(review.getId())
                .storeId(review.getStore().getId())
                .storeName(review.getStore().getName())
                .nickname(review.getMember().getNickname())
                .rating(review.getRating())
                .content(review.getContent())
                .createdAt(review.getCreatedAt())
                .ownerReply(ownerReplyContent)
                .ownerReplyCreatedAt(ownerReplyCreatedAt)
                .build();
    }
}
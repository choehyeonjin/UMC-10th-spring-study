package com.example.umc10th.domain.review.dto;

import java.util.List;

public class ReviewReqDTO {

    // 리뷰 작성
    public record CreateReviewDTO(
            Long storeId,
            Float rating,
            String content,
            List<Long> imageIds
    ) {}
}

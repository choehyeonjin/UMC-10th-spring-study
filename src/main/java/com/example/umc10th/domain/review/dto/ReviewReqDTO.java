package com.example.umc10th.domain.review.dto;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public class ReviewReqDTO {

    // 리뷰 작성
    public record CreateReviewDTO(

            @NotNull(message = "가게 ID는 필수입니다.")
            Long storeId,
            @NotNull(message = "별점은 필수입니다.")
            @DecimalMin(value = "0.0", message = "별점은 0.0 이상이어야 합니다.")
            @DecimalMax(value = "5.0", message = "별점은 5.0 이하이어야 합니다.")
            Float rating,
            String content,
            List<Long> imageIds
    ) {}
}

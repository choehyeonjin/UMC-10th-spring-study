package com.example.umc10th.domain.review.controller;

import com.example.umc10th.domain.review.converter.ReviewConverter;
import com.example.umc10th.domain.review.dto.ReviewReqDTO;
import com.example.umc10th.domain.review.dto.ReviewResDTO;
import com.example.umc10th.domain.review.entity.Review;
import com.example.umc10th.domain.review.exception.code.ReviewSuccessCode;
import com.example.umc10th.domain.review.service.ReviewService;
import com.example.umc10th.global.apiPayload.ApiResponse;
import com.example.umc10th.global.apiPayload.code.BaseSuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class ReviewController {

    private  final ReviewService reviewService;

    // 리뷰 작성
    @PostMapping("/reviews")
    public ApiResponse<ReviewResDTO.CreateReviewResultDTO> createReview(
            @RequestBody ReviewReqDTO.CreateReviewDTO request
    ) {
        Long memberId = 1L; // TODO: 인증 연동

        Review review = reviewService.createReview(memberId, request);

        ReviewResDTO.CreateReviewResultDTO resultDTO = ReviewConverter.toCreateReviewResultDTO(review);

        BaseSuccessCode code = ReviewSuccessCode.CREATED;
        return ApiResponse.onSuccess(
                code,
                resultDTO);
    }
}
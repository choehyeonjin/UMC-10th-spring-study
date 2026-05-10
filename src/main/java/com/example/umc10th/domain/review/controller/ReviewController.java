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
import org.springframework.web.bind.annotation.*;

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

    // 내가 작성한 리뷰 목록 조회
    @GetMapping("/members/me/reviews")
    public ApiResponse<ReviewResDTO.ReviewCursorPageDTO> getMyReviews(
            @RequestParam(defaultValue = "LATEST") String sort,
            @RequestParam(required = false) Long lastReviewId,
            @RequestParam(required = false) Float lastRating,
            @RequestParam(defaultValue = "3") Integer size
    ) {
        Long memberId = 1L; // TODO: 인증 연동

        ReviewResDTO.ReviewCursorPageDTO resultDTO = reviewService.getMyReviews(memberId, sort, lastReviewId, lastRating, size);

        BaseSuccessCode code = ReviewSuccessCode.OK;
        return ApiResponse.onSuccess(
                code,
                resultDTO);
    }
}
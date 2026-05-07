package com.example.umc10th.domain.review.service;

import com.example.umc10th.domain.review.dto.ReviewReqDTO;
import com.example.umc10th.domain.review.entity.Review;

public interface ReviewService {
    Review createReview(Long memberId, ReviewReqDTO.CreateReviewDTO request);
}
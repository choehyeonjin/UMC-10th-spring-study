package com.example.umc10th.domain.review.service;

import com.example.umc10th.domain.image.entity.Image;
import com.example.umc10th.domain.image.repository.ImageRepository;
import com.example.umc10th.domain.member.entity.Member;
import com.example.umc10th.domain.member.repository.MemberRepository;
import com.example.umc10th.domain.mission.entity.Store;
import com.example.umc10th.domain.mission.repository.StoreRepository;
import com.example.umc10th.domain.review.converter.ReviewConverter;
import com.example.umc10th.domain.review.dto.ReviewReqDTO;
import com.example.umc10th.domain.review.dto.ReviewResDTO;
import com.example.umc10th.domain.review.entity.Review;
import com.example.umc10th.domain.review.entity.ReviewImage;
import com.example.umc10th.domain.review.exception.ReviewException;
import com.example.umc10th.domain.review.exception.code.ReviewErrorCode;
import com.example.umc10th.domain.review.repository.ReviewImageRepository;
import com.example.umc10th.domain.review.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;
    private final ReviewImageRepository reviewImageRepository;
    private final MemberRepository memberRepository;
    private final StoreRepository storeRepository;
    private final ImageRepository imageRepository;

    // 리뷰 작성
    @Override
    @Transactional
    public Review createReview(Long memberId, ReviewReqDTO.CreateReviewDTO request) {
        // 엔티티 조회
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new ReviewException(ReviewErrorCode.MEMBER_NOT_FOUND));

        Store store = storeRepository.findById(request.storeId())
                .orElseThrow(() -> new ReviewException(ReviewErrorCode.STORE_NOT_FOUND));

        // Review 엔티티 생성 및 저장
        Review review = Review.builder()
                .member(member)
                .store(store)
                .rating(request.rating())
                .content(request.content())
                .build();

        Review savedReview = reviewRepository.save(review);

        // 첨부 이미지가 있는 경우 ReviewImage 매핑 및 저장
        if (request.imageIds() != null && !request.imageIds().isEmpty()) {
            List<ReviewImage> reviewImages = new ArrayList<>();

            for (int i = 0; i < request.imageIds().size(); i++) {
                Image image = imageRepository.findById(request.imageIds().get(i))
                        .orElseThrow(() -> new ReviewException(ReviewErrorCode.IMAGE_NOT_FOUND));

                ReviewImage reviewImage = ReviewImage.builder()
                        .review(savedReview)
                        .image(image)
                        .imageOrder(i + 1)
                        .build();

                reviewImages.add(reviewImage);
            }
            reviewImageRepository.saveAll(reviewImages);
        }

        return savedReview;
    }

    // 내 리뷰 조회
    @Override
    public ReviewResDTO.ReviewCursorPageDTO getMyReviews(Long memberId, String sort, Long lastReviewId, Float lastRating, Integer size) {

        Member member = memberRepository.findById(memberId).orElseThrow(() -> new ReviewException(ReviewErrorCode.MEMBER_NOT_FOUND));

        // PageRequest 생성
        PageRequest pageRequest = PageRequest.of(0, size);
        Slice<Review> reviewSlice;

        switch (sort.toUpperCase()) {
            case "LATEST":
                if (lastReviewId == null) {
                    reviewSlice = reviewRepository.findMyReviewsByLatest(member, pageRequest);
                } else {
                    reviewSlice = reviewRepository.findMyReviewsByLatestWithCursor(member, lastReviewId, pageRequest);
                }
                break;

            case "RATING":
                if (lastReviewId == null) {
                    reviewSlice = reviewRepository.findMyReviewsByRating(member, pageRequest);
                } else {
                    reviewSlice = reviewRepository.findMyReviewsByRatingWithCursor(member, lastRating, lastReviewId, pageRequest);
                }
                break;

            default: throw new ReviewException(ReviewErrorCode.INVALID_SORT_TYPE);
        }

        return ReviewConverter.toReviewCursorPageDTO(reviewSlice);
    }
}
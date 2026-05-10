package com.example.umc10th.domain.review.repository;

import com.example.umc10th.domain.member.entity.Member;
import com.example.umc10th.domain.mission.entity.Store;
import com.example.umc10th.domain.review.entity.Review;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    // 사용자가 해당 가게에 리뷰를 남겼는지 확인
    boolean existsByMemberAndStore(Member member, Store store);

    // 최신순: 커서가 없는 경우 (첫 페이지)
    @Query("SELECT r FROM Review r JOIN FETCH r.store JOIN FETCH r.member LEFT JOIN FETCH r.reviewReply WHERE r.member = :member ORDER BY r.id DESC")
    Slice<Review> findMyReviewsByLatest(@Param("member") Member member, Pageable pageable);

    // 최신순: 커서가 있는 경우
    @Query("SELECT r FROM Review r JOIN FETCH r.store JOIN FETCH r.member LEFT JOIN FETCH r.reviewReply WHERE r.member = :member AND r.id < :lastReviewId ORDER BY r.id DESC")
    Slice<Review> findMyReviewsByLatestWithCursor(@Param("member") Member member, @Param("lastReviewId") Long lastReviewId, Pageable pageable);

    // 별점순: 커서가 없는 경우 (첫 페이지)
    @Query("SELECT r FROM Review r JOIN FETCH r.store JOIN FETCH r.member LEFT JOIN FETCH r.reviewReply WHERE r.member = :member ORDER BY r.rating DESC, r.id DESC")
    Slice<Review> findMyReviewsByRating(@Param("member") Member member, Pageable pageable);

    // 별점순: 커서가 있는 경우
    @Query("SELECT r FROM Review r JOIN FETCH r.store JOIN FETCH r.member LEFT JOIN FETCH r.reviewReply WHERE r.member = :member " +
            "AND (r.rating < :lastRating OR (r.rating = :lastRating AND r.id < :lastReviewId)) " +
            "ORDER BY r.rating DESC, r.id DESC")
    Slice<Review> findMyReviewsByRatingWithCursor(
            @Param("member") Member member,
            @Param("lastRating") Float lastRating,
            @Param("lastReviewId") Long lastReviewId,
            Pageable pageable);
}

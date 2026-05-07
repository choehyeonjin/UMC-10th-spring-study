package com.example.umc10th.domain.review.repository;

import com.example.umc10th.domain.member.entity.Member;
import com.example.umc10th.domain.mission.entity.Store;
import com.example.umc10th.domain.review.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    // 사용자가 해당 가게에 리뷰를 남겼는지 확인
    boolean existsByMemberAndStore(Member member, Store store);
}

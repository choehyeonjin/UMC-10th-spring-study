package com.example.umc10th.domain.mission.repository;

import com.example.umc10th.domain.member.entity.Member;
import com.example.umc10th.domain.mission.entity.mapping.MemberMission;
import com.example.umc10th.domain.mission.enums.MissionStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MemberMissionRepository extends JpaRepository<MemberMission, Long> {

    Page<MemberMission> findAllByMemberAndStatus(Member member, MissionStatus status, Pageable pageable);
    // 특정 회원이 특정 지역에서 특정 상태(SUCCESS)로 완료한 미션 개수 카운트
    long countByMemberAndStatusAndMission_Store_Region_Id(Member member, MissionStatus status, Long regionId);

    @Query(value = "SELECT mm FROM MemberMission mm JOIN FETCH mm.mission m JOIN FETCH m.store s WHERE mm.member = :member AND mm.status = :status",
            countQuery = "SELECT count(mm) FROM MemberMission mm WHERE mm.member = :member AND mm.status = :status")
    Page<MemberMission> findMyMissions(@Param("member") Member member, @Param("status") MissionStatus status, Pageable pageable);
}
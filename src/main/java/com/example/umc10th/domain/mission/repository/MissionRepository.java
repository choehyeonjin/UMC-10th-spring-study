package com.example.umc10th.domain.mission.repository;

import com.example.umc10th.domain.mission.entity.Mission;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MissionRepository extends JpaRepository<Mission, Long> {
    // 특정 지역에 속한 가게들의 미션 목록 조회
    @Query(value = "SELECT m FROM Mission m JOIN FETCH m.store s JOIN FETCH s.region r WHERE r.id = :regionId",
            countQuery = "SELECT count(m) FROM Mission m JOIN m.store s WHERE s.region.id = :regionId")
    Page<Mission> findMissionsByRegionId(@Param("regionId") Long regionId, Pageable pageable);
}
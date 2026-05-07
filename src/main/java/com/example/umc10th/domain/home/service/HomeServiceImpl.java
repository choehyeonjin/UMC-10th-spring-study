package com.example.umc10th.domain.home.service;

import com.example.umc10th.domain.home.converter.HomeConverter;
import com.example.umc10th.domain.home.dto.HomeResDTO;
import com.example.umc10th.domain.home.exception.HomeException;
import com.example.umc10th.domain.home.exception.code.HomeErrorCode;
import com.example.umc10th.domain.mission.entity.Region;
import com.example.umc10th.domain.mission.repository.RegionRepository;
import com.example.umc10th.domain.member.entity.Member;
import com.example.umc10th.domain.member.repository.MemberRepository;
import com.example.umc10th.domain.mission.entity.Mission;
import com.example.umc10th.domain.mission.enums.MissionStatus;
import com.example.umc10th.domain.mission.repository.MemberMissionRepository;
import com.example.umc10th.domain.mission.repository.MissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class HomeServiceImpl implements HomeService {

    private final MemberRepository memberRepository;
    private final RegionRepository regionRepository;
    private final MissionRepository missionRepository;
    private final MemberMissionRepository memberMissionRepository;

    @Override
    public HomeResDTO.HomeViewDTO getHomeView(Long memberId, Long regionId, Integer page, Integer size) {

        // 회원 및 지역 조회
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new HomeException(HomeErrorCode.MEMBER_NOT_FOUND));

        Region region = regionRepository.findById(regionId)
                .orElseThrow(() -> new HomeException(HomeErrorCode.REGION_NOT_FOUND));

        // 해당 지역에서 회원이 완료한 미션 개수 조회
        long completedCount = memberMissionRepository.countByMemberAndStatusAndMission_Store_Region_Id(
                member, MissionStatus.SUCCESS, regionId
        );

        // 해당 지역의 미션 목록 페이징 조회
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<Mission> missionPage = missionRepository.findMissionsByRegionId(regionId, pageRequest);

        return HomeConverter.toHomeViewDTO(region, completedCount, missionPage);
    }
}
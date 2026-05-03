package com.example.umc10th.domain.mission.service;

import com.example.umc10th.domain.member.entity.Member;
import com.example.umc10th.domain.member.repository.MemberRepository;
import com.example.umc10th.domain.mission.converter.MissionConverter;
import com.example.umc10th.domain.mission.dto.MissionResDTO;
import com.example.umc10th.domain.mission.entity.mapping.MemberMission;
import com.example.umc10th.domain.mission.enums.MissionStatus;
import com.example.umc10th.domain.mission.exception.MissionException;
import com.example.umc10th.domain.mission.exception.code.MissionErrorCode;
import com.example.umc10th.domain.mission.repository.MemberMissionRepository;
import com.example.umc10th.domain.review.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MissionServiceImpl implements MissionService {

    private final MemberRepository memberRepository;
    private final MemberMissionRepository memberMissionRepository;
    private final ReviewRepository reviewRepository;

    @Override
    public MissionResDTO.MissionListDTO getMyMissions(Long memberId, String statusStr, Integer page, Integer size) {

        // 회원 검증
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new MissionException(MissionErrorCode.MEMBER_NOT_FOUND));

        // 상태 문자열 검증
        MissionStatus status;
        try {
            status = MissionStatus.valueOf(statusStr.toUpperCase());
            if (status != MissionStatus.IN_PROGRESS && status != MissionStatus.SUCCESS) {
                // NOT_STARTED나 CANCELLED가 들어오면 예외 처리
                throw new IllegalArgumentException();
            }
        } catch (IllegalArgumentException e) {
            throw new MissionException(MissionErrorCode.INVALID_MISSION_STATUS);
        }

        // 미션 목록 페이징 조회
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<MemberMission> missionPage = memberMissionRepository.findAllByMemberAndStatus(member, status, pageRequest);

        // 각 미션별 리뷰 작성 여부 확인
        List<Boolean> reviewWrittenList = missionPage.getContent().stream()
                .map(memberMission -> {
                    // 미션이 SUCCESS 상태일 때만 리뷰 작성 여부를 DB에서 확인
                    if (status == MissionStatus.SUCCESS) {
                        return reviewRepository.existsByMemberAndStore(member, memberMission.getMission().getStore());
                    }
                    return false;
                })
                .collect(Collectors.toList());

        return MissionConverter.toMissionListDTO(missionPage, reviewWrittenList);
    }
}
package com.fastcampus.jober.domain.spacewallmember.service;

import com.fastcampus.jober.domain.member.domain.Member;
import com.fastcampus.jober.domain.member.repository.MemberRepository;
import com.fastcampus.jober.domain.spacewallmember.domain.SpaceWallMember;
import com.fastcampus.jober.domain.spacewallmember.dto.SpaceWallMemberRequest;
import com.fastcampus.jober.domain.spacewallmember.repository.SpaceWallMemberRepository;
import com.fastcampus.jober.domain.spacewallmember.repository.SpaceWallPermissionRepository;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class SpaceWallMemberService {

    private final SpaceWallMemberRepository spaceWallMemberRepository;
    private final SpaceWallPermissionRepository spaceWallPermissionRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public void saveSpaceWallMember(Long spaceWallId, List<SpaceWallMemberRequest.AssignDTO> requests) {

        // 공유 멤버 등록된 email인지 체크
        for (SpaceWallMemberRequest.AssignDTO request : requests) {
            Member member = memberRepository.findByEmail(request.getEmail()).get();
            // TODO - Member에 등록되지 않은 email인 경우 체크, 예외처리? 또는 무시?

            SpaceWallMember assignedMember =
                    spaceWallMemberRepository.findSpaceWallMemberByEmail(spaceWallId, member.getId());

            // 공유 멤버 등록되어 있지 않은 경우
            if (assignedMember == null) {
                // 공유스페이스 멤버 등록
                spaceWallMemberRepository.insertMember(member.getId(), spaceWallId);
                // 공유스페이스 멤버 권한 추가
                spaceWallPermissionRepository.insertPermission(
                        spaceWallMemberRepository.findSpaceWallMemberByEmail(spaceWallId, member.getId()).getId(),
                        request.getAuths()
                );
                continue;
            }
            // 공유 멤버 등록되어 있는 경우
            spaceWallPermissionRepository.updatePermission(assignedMember.getId(), request.getAuths());
        }
    }
}

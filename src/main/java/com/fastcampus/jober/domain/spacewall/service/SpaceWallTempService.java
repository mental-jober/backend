package com.fastcampus.jober.domain.spacewall.service;

import com.fastcampus.jober.domain.member.domain.Member;
import com.fastcampus.jober.domain.member.repository.MemberRepository;
import com.fastcampus.jober.domain.spacewall.domain.SpaceWallLayout;
import com.fastcampus.jober.domain.spacewall.domain.SpaceWallTemp;
import com.fastcampus.jober.domain.spacewall.domain.Workspace;
import com.fastcampus.jober.domain.spacewall.dto.SpaceWallTempDTO;
import com.fastcampus.jober.domain.spacewall.repository.SpaceWallLayoutRepository;
import com.fastcampus.jober.domain.spacewall.repository.SpaceWallTempRepository;
import com.fastcampus.jober.domain.spacewall.repository.WorkspaceRepository;
import com.fastcampus.jober.global.auth.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SpaceWallTempService {

    private final SpaceWallTempRepository spaceWallTempRepository;
    private final MemberRepository memberRepository;
    private final SpaceWallLayoutRepository spaceWallLayoutRepository;
    private final WorkspaceRepository workspaceRepository;
    private final JwtTokenProvider jwtTokenProvider;

    @Transactional
    public SpaceWallTemp saveTemporarySpaceWall(String token, SpaceWallTempDTO.SaveDTO saveDTO) {
        Long memberId = jwtTokenProvider.getMemberId(token);

        Member member = memberRepository.findById(saveDTO.getCreateMemberId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid memberId"));

        SpaceWallLayout layout = spaceWallLayoutRepository.findById(saveDTO.getLayoutId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid layoutId"));

        Workspace workspace = workspaceRepository.findById(saveDTO.getWorkspaceId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid workspaceId"));

        SpaceWallTemp spaceWallTemp = SpaceWallTemp.builder()
                .layout(layout)
                .createMember(member)
                .workspace(workspace)
                .url(saveDTO.getUrl())
                .title(saveDTO.getTitle())
                .description(saveDTO.getDescription())
                .profileImageUrl(saveDTO.getProfileImageUrl())
                .backgroundImageUrl(saveDTO.getBackgroundImageUrl())
                .pathIds(saveDTO.getPathIds())
                .shareUrl(saveDTO.getShareUrl())
                .shareExpiredAt(saveDTO.getShareExpiredAt())
                .sequence(saveDTO.getSequence())
                .build();

        return spaceWallTempRepository.save(spaceWallTemp);
    }

    @Transactional(readOnly = true)
    public Optional<SpaceWallTemp> getTemporarySpaceWall(String token) {
        Long memberId = jwtTokenProvider.getMemberId(token);
        return spaceWallTempRepository.findByCreateMember_Id(memberId);
    }

    @Transactional
    public void deleteTemporarySpaceWall(String token) {
        Long memberId = jwtTokenProvider.getMemberId(token);
        spaceWallTempRepository.deleteByCreateMember_Id(memberId);

        // 삭제 검증
        if (spaceWallTempRepository.findByCreateMember_Id(memberId).isPresent()) {
            throw new RuntimeException("삭제 실패: " + memberId);
        }
    }
}

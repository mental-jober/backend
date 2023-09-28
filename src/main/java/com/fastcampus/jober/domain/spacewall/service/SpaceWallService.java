package com.fastcampus.jober.domain.spacewall.service;

import com.fastcampus.jober.domain.member.domain.Member;
import com.fastcampus.jober.domain.member.repository.MemberRepository;
import com.fastcampus.jober.domain.spacewall.domain.SpaceWall;
import com.fastcampus.jober.domain.spacewall.dto.SpaceWallRequest;
import com.fastcampus.jober.domain.spacewall.dto.SpaceWallResponse;
import com.fastcampus.jober.domain.spacewall.dto.SpaceWallResponse.SessionDTO;
import com.fastcampus.jober.domain.spacewall.repository.SpaceWallRepository;
import com.fastcampus.jober.global.auth.session.MemberDetails;
import com.fastcampus.jober.global.error.exception.SpaceWallBadRequestException;
import com.fastcampus.jober.global.error.exception.SpaceWallNotFoundException;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class SpaceWallService {

    private final SpaceWallRepository spaceWallRepository;
    private final StringRedisTemplate redisTemplate;
    private final MemberRepository memberRepository;

    @Transactional
    public SpaceWallResponse.ResponseDto create(SpaceWallRequest.CreateDto createDto, MemberDetails memberDetails) {
        if (createDto == null) {
            throw new SpaceWallBadRequestException("생성된 DTO가 잘못되었습니다.");
        }

        Long currentMemberId = memberDetails.getMember().getId();
        Member currentMember = memberRepository.findById(currentMemberId)
                .orElseThrow(() -> new RuntimeException("ID가 있는 회원을 찾을 수 없습니다.: " + currentMemberId));

        SpaceWall spaceWall = createDto.toEntityWithMember(currentMember);
        SpaceWall savedSpaceWall = spaceWallRepository.save(spaceWall);
        return new SpaceWallResponse.ResponseDto(savedSpaceWall);
    }

    @Transactional(readOnly = true)
    public SpaceWallResponse.ResponseDto findById(Long id) {
        if (id == null) {
            throw new SpaceWallBadRequestException("공유페이지 ID는 null일 수 없습니다.");
        }

        SpaceWall spaceWall = spaceWallRepository.findById(id)
                .orElseThrow(() -> new SpaceWallNotFoundException("ID가 있는 공유페이지을 찾을 수 없습니다: " + id));
        return new SpaceWallResponse.ResponseDto(spaceWall);
    }

    @Transactional
    public SpaceWallResponse.ResponseDto update(Long id, SpaceWallRequest.UpdateDto updateDto, MemberDetails memberDetails) {
        if (id == null || updateDto == null) {
            throw new SpaceWallBadRequestException("업데이트할 매개 변수가 잘못되었습니다.");
        }

        SpaceWall spaceWall = spaceWallRepository.findById(id)
                .orElseThrow(() -> new SpaceWallNotFoundException("ID가 있는 공유페이지을 찾을 수 없습니다: " + id));

        SpaceWall updatedSpaceWall = updateDto.toEntity();
        spaceWall.update(updatedSpaceWall);

        return new SpaceWallResponse.ResponseDto(spaceWall);
    }

    @Transactional
    public void delete(Long id, MemberDetails memberDetails) {
        if (id == null) {
            throw new SpaceWallBadRequestException("공유페이지 ID는 null일 수 없습니다.");
        }

        SpaceWall spaceWall = spaceWallRepository.findById(id)
                .orElseThrow(() -> new SpaceWallNotFoundException("ID가 있는 공유페이지을 찾을 수 없습니다.: " + id));

        if ("1".equals(spaceWall.getPathIds())) {
            throw new SpaceWallBadRequestException("최상단의 공유페이지는 삭제할 수 없습니다.");
        }

        spaceWallRepository.delete(spaceWall);
    }

    public SpaceWallResponse.SessionDTO checkEditSession(Long memberId, Long spaceWallId,
                                                         HttpSession httpSession) {
        boolean accessable = isNotExistSession(spaceWallId, httpSession);

        if (accessable) {
            addEditSession(memberId, spaceWallId, httpSession);
        }

        SessionDTO sessionDTO = new SessionDTO(accessable);

        return sessionDTO;
    }

    private boolean isNotExistSession(Long spaceWallId, HttpSession httpSession) {
        Long memberId = (Long) httpSession.getAttribute("spaces/" + spaceWallId);

        if (memberId == null) {
            return true;
        }

        return false;
    }

    private void addEditSession(Long memberId, Long spaceWallId, HttpSession httpSession) {
        httpSession.setAttribute("spaces/" + spaceWallId, memberId);
        httpSession.setMaxInactiveInterval(13);
    }

    private void removeEditSession(Long spaceWallId, HttpSession httpSession) {
        httpSession.removeAttribute("spaces/" + spaceWallId);
    }


    @Transactional(readOnly = true)
    public boolean checkSpaceWallIdExists(Long id) {
        return spaceWallRepository.existsSpaceWallById(id);
    }
}
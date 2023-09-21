package com.fastcampus.jober.domain.spacewall.service;

import com.fastcampus.jober.domain.spacewall.domain.SpaceWall;
import com.fastcampus.jober.domain.spacewall.dto.SpaceWallRequest;
import com.fastcampus.jober.domain.spacewall.dto.SpaceWallResponse;
import com.fastcampus.jober.domain.spacewall.repository.SpaceWallRepository;
import com.fastcampus.jober.global.error.exception.SpaceWallBadRequestException;
import com.fastcampus.jober.global.error.exception.SpaceWallNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class SpaceWallService {

    private final SpaceWallRepository spaceWallRepository;

    @Transactional
    public SpaceWallResponse.ResponseDto create(SpaceWallRequest.CreateDto createDto) {
        if (createDto == null) {
            throw new SpaceWallBadRequestException("생성된 DTO가 잘못되었습니다.");
        }

        SpaceWall spaceWall = createDto.toEntity();
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
    public SpaceWallResponse.ResponseDto update(Long id, SpaceWallRequest.UpdateDto updateDto) {
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
    public void delete(Long id) {
        if (id == null) {
            throw new SpaceWallBadRequestException("공유페이지 ID는 null일 수 없습니다.");
        }

        SpaceWall spaceWall = spaceWallRepository.findById(id)
                .orElseThrow(() -> new SpaceWallNotFoundException("ID가 있는 공유페이지을 찾을 수 없습니다.: " + id));

        spaceWallRepository.delete(spaceWall);
    }
}

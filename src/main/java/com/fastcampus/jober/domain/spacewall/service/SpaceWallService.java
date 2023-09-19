package com.fastcampus.jober.domain.spacewall.service;

import com.fastcampus.jober.domain.spacewall.domain.SpaceWall;
import com.fastcampus.jober.domain.spacewall.dto.SpaceWallDto;
import com.fastcampus.jober.domain.spacewall.repository.SpaceWallRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class SpaceWallService {

    private final SpaceWallRepository spaceWallRepository;

    @Transactional
    public SpaceWallDto.ResponseDto create(SpaceWallDto.CreateDto createDto) {
        SpaceWall spaceWall = createDto.toEntity();
        SpaceWall savedSpaceWall = spaceWallRepository.save(spaceWall);
        return new SpaceWallDto.ResponseDto(savedSpaceWall);
    }

    @Transactional(readOnly = true)
    public SpaceWallDto.ResponseDto findById(Long id) {
        return spaceWallRepository.findById(id)
                .map(SpaceWallDto.ResponseDto::new)
                .orElse(null);
    }

    @Transactional
    public SpaceWallDto.ResponseDto update(Long id, SpaceWallDto.UpdateDto updateDto) {
        SpaceWall spaceWall = spaceWallRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("잘못된 SpaceWall Id: " + id));

        spaceWall.update(updateDto);

        return new SpaceWallDto.ResponseDto(spaceWall);
    }

    @Transactional
    public void delete(Long id) {
        if (!spaceWallRepository.existsById(id)) {
            throw new IllegalArgumentException("잘못된 SpaceWall Id: " + id);
        }
        spaceWallRepository.deleteById(id);
    }
}

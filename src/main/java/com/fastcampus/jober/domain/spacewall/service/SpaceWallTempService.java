package com.fastcampus.jober.domain.spacewall.service;

import com.fastcampus.jober.domain.spacewall.domain.SpaceWallTemp;
import com.fastcampus.jober.domain.spacewall.dto.SpaceWallTempDTO;
import com.fastcampus.jober.domain.spacewall.repository.SpaceWallTempRepository;
import com.fastcampus.jober.global.error.exception.SpaceWallBadRequestException;
import com.fastcampus.jober.global.error.exception.SpaceWallNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class SpaceWallTempService {

    private final SpaceWallTempRepository spaceWallTempRepository;

    @Transactional
    public SpaceWallTempDTO.TempResponseDTO saveTemporary(SpaceWallTempDTO.TempSaveDTO tempSaveDto) {
        if (tempSaveDto == null) {
            throw new SpaceWallBadRequestException("TempDTO is wrong.");
        }

        SpaceWallTemp spaceWallTemp = tempSaveDto.toEntity();
        SpaceWallTemp savedSpaceWallTemp = spaceWallTempRepository.save(spaceWallTemp);
        return new SpaceWallTempDTO.TempResponseDTO(savedSpaceWallTemp);
    }

    @Transactional(readOnly = true)
    public SpaceWallTempDTO.TempResponseDTO getTemporary(Long id) {
        if (id == null) {
            throw new SpaceWallBadRequestException("임시 공유페이지 ID는 null일 수 없습니다.");
        }

        SpaceWallTemp spaceWallTemp = spaceWallTempRepository.findById(id)
            .orElseThrow(() -> new SpaceWallNotFoundException("ID가 있는 임시 공유페이지를 찾을 수 없습니다: " + id));
        return new SpaceWallTempDTO.TempResponseDTO(spaceWallTemp);
    }

    @Transactional
    public void deleteTemporary(Long id) {
        if (id == null) {
            throw new SpaceWallBadRequestException("임시 공유페이지 ID는 null일 수 없습니다.");
        }

        SpaceWallTemp spaceWallTemp = spaceWallTempRepository.findById(id)
            .orElseThrow(() -> new SpaceWallNotFoundException("ID가 있는 임시 공유페이지를 찾을 수 없습니다.: " + id));

        spaceWallTempRepository.delete(spaceWallTemp);
    }
}

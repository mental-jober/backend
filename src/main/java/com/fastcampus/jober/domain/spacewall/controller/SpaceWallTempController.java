package com.fastcampus.jober.domain.spacewall.controller;

import com.fastcampus.jober.domain.spacewall.dto.SpaceWallTempDTO;
import com.fastcampus.jober.domain.spacewall.service.SpaceWallTempService;
import com.fastcampus.jober.global.utils.api.dto.ResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/spaces/temp")
@RequiredArgsConstructor
public class SpaceWallTempController {

    private final SpaceWallTempService spaceWallTempService;

    @PostMapping
    public ResponseEntity<ResponseDTO<SpaceWallTempDTO.TempResponseDTO>> saveTemporarySpaceWall(@RequestBody SpaceWallTempDTO.TempSaveDTO tempSaveDto) {
        SpaceWallTempDTO.TempResponseDTO savedSpaceWallTempDto = spaceWallTempService.saveTemporary(tempSaveDto);
        if (savedSpaceWallTempDto == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(new ResponseDTO<>(HttpStatus.CREATED, "임시 저장되었습니다.", savedSpaceWallTempDto), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDTO<SpaceWallTempDTO.TempResponseDTO>> getTemporarySpaceWall(@PathVariable Long id) {
        SpaceWallTempDTO.TempResponseDTO foundSpaceWallTempDto = spaceWallTempService.getTemporary(id);
        return ResponseEntity.ok(new ResponseDTO<>(HttpStatus.OK, "완료되었습니다.", foundSpaceWallTempDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDTO<String>> deleteTemporarySpaceWall(@PathVariable Long id) {
        spaceWallTempService.deleteTemporary(id);
        return new ResponseEntity<>(new ResponseDTO<>(HttpStatus.OK, "임시 공유페이지 " + id + " 삭제되었습니다.", "Success"), HttpStatus.OK);
    }
}

package com.fastcampus.jober.domain.spacewallmember.controller;

import com.fastcampus.jober.domain.spacewallmember.dto.SpaceWallMemberRequest;
import com.fastcampus.jober.domain.spacewallmember.service.SpaceWallMemberService;
import com.fastcampus.jober.global.utils.api.dto.ResponseDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/spaces")
@Slf4j
public class SpaceWallMemberController {

    private final SpaceWallMemberService spaceWallMemberService;

    /**
     * 공유 멤버 등록 시 객체 리스트를 Json 데이터로 받아 정보를 갱신합니다.<br>
     * 1. 멤버 및 권한 갱신<br>
     * 2. 요청 데이터 수와 DB 데이터 수 비교<br>
     * 3. 요청 데이터 이메일과 DB 데이터 이메일 비교해서 요청 데이터 이메일에 없는 DB 이메일을 리턴<br>
     * 4. 이메일로 공동 작업자 삭제<br>
     * @param spaceWallId 공유스페이스 id
     * @param requests 공유멤버로 등록하려는 멤버 정보
     * @return 확인 메세지
     */
    @PostMapping("/{spaceWallId}/member")
    public ResponseEntity<?> spaceWallMemberAdd(
            @PathVariable Long spaceWallId,
            @Valid @RequestBody List<SpaceWallMemberRequest.AssignDTO> requests
    ) {
        spaceWallMemberService.saveSpaceWallMember(spaceWallId, requests);

        return ResponseEntity.ok(new ResponseDTO<>("공동 작업자 정보를 갱신했습니다."));
    }

    /**
     * 공유스페이스의 공동 작업자를 조회합니다.
     * @param spaceWallId 공유스페이스 id
     * @return 공유스페이스 공동 작업자 정보
     */
    @GetMapping("/{spaceWallId}/member")
    public ResponseEntity<?> spaceWallMemberList(@PathVariable Long spaceWallId) {

        return ResponseEntity.ok(
                new ResponseDTO<>(
                        spaceWallMemberService.findSpaceWallMember(spaceWallId),
                        "공동 작업자 정보를 조회합니다.")
        );
    }
}

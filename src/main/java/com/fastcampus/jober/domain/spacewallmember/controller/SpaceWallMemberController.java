package com.fastcampus.jober.domain.spacewallmember.controller;

import com.fastcampus.jober.domain.spacewall.service.SpaceWallService;
import com.fastcampus.jober.domain.spacewallmember.dto.SpaceWallMemberRequest;
import com.fastcampus.jober.domain.spacewallmember.service.SpaceWallMemberService;
import com.fastcampus.jober.global.error.exception.SpaceWallNotFoundException;
import com.fastcampus.jober.global.utils.api.dto.ResponseDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.fastcampus.jober.global.constant.ErrorCode.INVALID_REQUEST;

@RestController
@RequiredArgsConstructor
@RequestMapping("/spaces")
@Slf4j
public class SpaceWallMemberController {

    private final SpaceWallMemberService spaceWallMemberService;
    private final SpaceWallService spaceWallService;

    /**
     * 공유 멤버 등록 시 객체 리스트를 Json 데이터로 받아 정보를 갱신합니다.<br>
     * 1. 이미 공유스페이스에 공동작업자로 등록되어 있는 Email : 권한 수정<br>
     * 2. 공유스페이스에 공동작업자로 등록되어 있지 않은 Email : INSERT INTO space_wall_member <br>
     * 3. 요청데이터와 등록된 공동작업자(DB)의 사이즈를 비교하고 요청데이터에 없는 Email : 삭제
     * @param spaceWallId 공유스페이스 id
     * @param requests 공동작업자로 등록하려는 멤버 정보 (String email, Auths auths)
     * @return 확인 메세지
     */
    @PostMapping("/{spaceWallId}/member")
    public ResponseEntity<?> spaceWallMemberAdd(
            @PathVariable Long spaceWallId,
            @Valid @RequestBody List<SpaceWallMemberRequest.AssignDTO> requests
    ) {
        if (!spaceWallService.checkSpaceWallIdExists(spaceWallId))
            throw new SpaceWallNotFoundException(INVALID_REQUEST.getMessage());

        spaceWallMemberService.saveSpaceWallMember(spaceWallId, requests);

        return ResponseEntity.ok(new ResponseDTO<>("공동 작업자 정보를 갱신했습니다."));
    }

    /**
     * 공유스페이스의 공동작업자를 조회합니다.
     * @param spaceWallId 공유스페이스 id
     * @return 공유스페이스 공동작업자 정보
     */
    @GetMapping("/{spaceWallId}/member")
    public ResponseEntity<?> spaceWallMemberList(@PathVariable Long spaceWallId) {
        if (!spaceWallService.checkSpaceWallIdExists(spaceWallId))
            throw new SpaceWallNotFoundException(INVALID_REQUEST.getMessage());

        return ResponseEntity.ok(
                new ResponseDTO<>(
                        spaceWallMemberService.findSpaceWallMember(spaceWallId),
                        "공동 작업자 정보를 조회합니다.")
        );
    }
}

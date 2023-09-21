package com.fastcampus.jober.domain.spacewallmember.controller;

import com.fastcampus.jober.global.auth.session.SpaceWallContextHolder;
import com.fastcampus.jober.domain.spacewallmember.dto.SpaceWallMemberRequest;
import com.fastcampus.jober.domain.spacewallmember.service.SpaceWallMemberService;
import com.fastcampus.jober.global.utils.api.dto.ResponseDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.annotation.Before;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/spaces")
public class SpaceWallMemberController {

    private final SpaceWallMemberService spaceWallMemberService;

    /**
     * 공동작업자 저장 시, 이후
     * 페이지에 추가되어있는 목록 개수와
     * 실제 DB에서의 email 개수를 비교하고
     * 다른 경우
     * DB의 email들을 추가되어있는 email들에서 검색해보고 없는 경우 삭제.
     */

    /**
     * 공유 멤버 등록 시 객체 리스트를 Json 데이터로 받아 정보를 갱신합니다.
     * @param spaceWallId : 공유스페이스 id
     * @param requests : 공유멤버로 등록하려는 멤버 정보
     * @return : 확인 메세지
     */
    @PostMapping("/member/{spaceWallId}")
    public ResponseEntity<?> spaceWallMemberAdd(
            @PathVariable Long spaceWallId,
            @Valid @RequestBody List<SpaceWallMemberRequest.AssignDTO> requests
    ) {
        // 멤버 등록 및 권한 수정 로직
        spaceWallMemberService.saveSpaceWallMember(spaceWallId, requests);

        return ResponseEntity.ok(new ResponseDTO<>("공유 멤버 및 권한 등록했습니다."));
    }

    /**
     * 공유스페이스 멤버를 조회합니다.
     * @param spaceWallId : 공유스페이스 id
     * @return : 공유스페이스 멤버 정보
     */
    @GetMapping("/member/{spaceWallId}")
    public ResponseEntity<?> spaceWallMemberList(@PathVariable Long spaceWallId) {

        return ResponseEntity.ok(
                new ResponseDTO<>(spaceWallMemberService.findSpaceWallMember(spaceWallId), "공유 멤버를 조회합니다.")
        );
    }
}

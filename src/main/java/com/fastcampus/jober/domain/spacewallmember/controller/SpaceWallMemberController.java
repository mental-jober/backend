package com.fastcampus.jober.domain.spacewallmember.controller;

import com.fastcampus.jober.domain.spacewallmember.dto.SpaceWallMemberRequest;
import com.fastcampus.jober.domain.spacewallmember.service.SpaceWallMemberService;
import com.fastcampus.jober.global.utils.api.dto.ResponseDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class SpaceWallMemberController {

    private final SpaceWallMemberService spaceWallMemberService;

    /**
     * 공유 멤버 등록 시 객체 리스트를 Json 데이터로 받아 정보를 갱신합니다.
     * @param spaceWallId : 공유스페이스 id
     * @param requests : 공유멤버로 등록하려는 멤버 정보
     * @return : 확인 메세지
     */
    @PostMapping("/spaces/{spaceWallId}/member")
    public ResponseEntity<?> spaceWallMemberAdd (
            @PathVariable Long spaceWallId,
            @Valid @RequestBody List<SpaceWallMemberRequest.AssignDTO> requests
    ) {
        // TODO - API 호출자가 권한을 가지고 있는지 체크

        // 멤버 등록 및 권한 수정 로직
        spaceWallMemberService.saveSpaceWallMember(spaceWallId, requests);

        return ResponseEntity.ok(new ResponseDTO<>("공유 멤버 및 권한 등록했습니다."));
    }
}

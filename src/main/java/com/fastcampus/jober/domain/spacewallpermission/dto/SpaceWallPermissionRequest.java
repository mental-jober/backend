package com.fastcampus.jober.domain.spacewallpermission.dto;

import com.fastcampus.jober.global.constant.Auths;
import com.fastcampus.jober.global.constant.Type;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SpaceWallPermissionRequest {
    private Long spaceWallId;  // 공유 스페이스 ID
    private Long memberId;     // 회원 ID
    private Auths auths;       // READ / EDIT / DELETE
    private Long targetSequence;    // 상위 페이지 ID
}

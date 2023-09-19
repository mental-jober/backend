package com.fastcampus.jober.domain.spacewallpermission.dto;

import com.fastcampus.jober.global.constant.Auths;
import com.fastcampus.jober.global.constant.Type;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SpaceWallPermissionRequestDto {
    private Long spaceWallId;  // 공유 스페이스 ID
    private Long memberId;     // 회원 ID
    private Type type;         // WHITE / BLACK
    private Auths auths;       // READ / EDIT / DELETE
    private Long parentId;     // 상위 페이지 ID
}

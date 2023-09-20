package com.fastcampus.jober.domain.spacewallpermission.dto;

import com.fastcampus.jober.domain.spacewallpermission.domain.SpaceWallPermission;
import com.fastcampus.jober.global.constant.Auths;
import com.fastcampus.jober.global.constant.Type;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SpaceWallPermissionResponse {
    private Long id;
    private Long spaceWallId;  // 공유 스페이스 ID
    private Long memberId;     // 회원 ID
    private Type type;         // WHITE / BLACK
    private Auths auths;       // READ / EDIT / DELETE
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Long parentId;     // 상위 페이지 ID

    public SpaceWallPermissionResponse(SpaceWallPermission spaceWallPermission) {
        this.id = spaceWallPermission.getId();
        this.spaceWallId = spaceWallPermission.getSpaceWall().getId();
        this.memberId = spaceWallPermission.getMember().getId();
        this.type = spaceWallPermission.getType();
        this.auths = spaceWallPermission.getAuths();
        this.createdAt = spaceWallPermission.getCreatedAt();
        this.updatedAt = spaceWallPermission.getUpdatedAt();
        this.parentId = spaceWallPermission.getParentId();
    }
}

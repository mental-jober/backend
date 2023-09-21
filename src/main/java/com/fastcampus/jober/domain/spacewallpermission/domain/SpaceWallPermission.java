package com.fastcampus.jober.domain.spacewallpermission.domain;

import com.fastcampus.jober.domain.BaseTimeEntity;
import com.fastcampus.jober.domain.member.domain.Member;
import com.fastcampus.jober.domain.spacewall.domain.SpaceWall;
import com.fastcampus.jober.domain.spacewallmember.domain.SpaceWallMember;
import com.fastcampus.jober.global.constant.Auths;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Data
@EqualsAndHashCode(callSuper = false)
public class SpaceWallPermission extends BaseTimeEntity {

    @Id
    @Column(nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //    @JsonIgnore
    @OneToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "space_wall_member_id")
    @PrimaryKeyJoinColumn
    private SpaceWallMember spaceWallMember;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private Auths auths = Auths.VIEWER; // READ를 기본값으로 설정

    @Column
    private Long parentId; // 상위 페이지 ID

    public void setAuths(Auths auths) {
        this.auths = auths;
    }

}

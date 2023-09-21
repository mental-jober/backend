package com.fastcampus.jober.domain.spacewallpermission.domain;

import com.fastcampus.jober.domain.member.domain.Member;
import com.fastcampus.jober.domain.BaseTimeEntity;
import com.fastcampus.jober.domain.spacewall.domain.SpaceWall;
import com.fastcampus.jober.domain.spacewallmember.domain.SpaceWallMember;
import com.fastcampus.jober.global.constant.Auths;
import com.fastcampus.jober.global.constant.Type;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Data
public class SpaceWallPermission extends BaseTimeEntity {

    @Id
    @Column(nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //    @JsonIgnore
    @OneToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @PrimaryKeyJoinColumn
    private SpaceWall spaceWall;

    //    @JsonIgnore
    @OneToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "space_wall_member_id")
    @PrimaryKeyJoinColumn
    private SpaceWallMember spaceWallMember;

    //    @JsonIgnore
    @OneToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @PrimaryKeyJoinColumn
    private Member member;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private Type type = Type.WHITE; // WHITE를 기본값으로 설정

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private Auths auths = Auths.VIEWER; // READ를 기본값으로 설정

    @Column
    private Long parentId; // 상위 페이지 ID

    public void setAuths(Auths auths) {
        this.auths = auths;
    }

}

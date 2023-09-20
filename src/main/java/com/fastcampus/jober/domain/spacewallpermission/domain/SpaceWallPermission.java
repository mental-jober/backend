package com.fastcampus.jober.domain.spacewallpermission.domain;

import com.fastcampus.jober.domain.BaseTimeEntity;
import com.fastcampus.jober.domain.spaceWallMember.domain.SpaceWallMember;
import com.fastcampus.jober.domain.spacewall.domain.SpaceWall;
import com.fastcampus.jober.domain.spacewallmember.domain.SpaceWallMember;
import com.fastcampus.jober.global.constant.Auths;
import com.fastcampus.jober.global.constant.Type;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrimaryKeyJoinColumn;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
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
    @JoinColumn(name = "space_wall_member_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @PrimaryKeyJoinColumn
    private SpaceWallMember spaceWallMember;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private Type type; // WHITE / BLACK

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private Auths auths; // READ / EDIT / DELETE

}

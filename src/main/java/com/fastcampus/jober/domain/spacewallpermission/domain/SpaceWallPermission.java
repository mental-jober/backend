package com.fastcampus.jober.domain.spacewallpermission.domain;

import com.fastcampus.jober.domain.BaseTimeEntity;
import com.fastcampus.jober.domain.spacewallmember.domain.SpaceWallMember;
import com.fastcampus.jober.global.constant.Auths;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
    @JsonManagedReference
    @JoinColumn(name = "space_wall_member_id", nullable = false)
    private SpaceWallMember spaceWallMember;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private Auths auths = Auths.VIEWER; // READ를 기본값으로 설정

    @Column
    private Long parentId;

    public void setAuths(Auths auths) {
        this.auths = auths;
    }

}

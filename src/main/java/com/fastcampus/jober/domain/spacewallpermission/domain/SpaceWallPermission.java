package com.fastcampus.jober.domain.spacewallpermission.domain;

import com.fastcampus.jober.domain.member.domain.Member;
import com.fastcampus.jober.domain.spacewall.domain.SpaceWall;
import com.fastcampus.jober.domain.spacewallmember.domain.SpaceWallMember;
import com.fastcampus.jober.global.constant.Auths;
import com.fastcampus.jober.global.constant.Type;
import jakarta.persistence.*;

import java.time.LocalDateTime;

import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

@Entity
@Data
public class SpaceWallPermission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
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
    private SpaceWallMember spaceWallMember;

    //    @JsonIgnore
    @OneToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @PrimaryKeyJoinColumn
    private Member member;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private Type type; // WHITE / BLACK

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private Auths auths; // READ / EDIT / DELETE

    @Column
    private Long parentId; // 상위 페이지 ID

    @Column(nullable = false)
    @CreatedDate
    private LocalDateTime createdAt;

    @Column
    @LastModifiedDate
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() { this.updatedAt = LocalDateTime.now();  }

    public void setAuths(Auths auths) {
        this.auths = auths;
    }
}

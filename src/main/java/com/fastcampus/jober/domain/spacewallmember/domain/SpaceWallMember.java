package com.fastcampus.jober.domain.spacewallmember.domain;

import com.fastcampus.jober.domain.BaseTimeEntity;
import com.fastcampus.jober.domain.member.domain.Member;
import com.fastcampus.jober.domain.spacewall.domain.SpaceWall;
import com.fastcampus.jober.domain.spacewallpermission.domain.SpaceWallPermission;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(
        name = "space_wall_member",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"space_wall_id", "member_id"})
})
@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class SpaceWallMember extends BaseTimeEntity {

    @Id
    @Column(nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonManagedReference
    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "member_id")
    private Member member;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "space_wall_id")
    private SpaceWall spaceWall;

    /**
     *  양방향 참조 테스트
     */
    @OneToOne(mappedBy = "spaceWallMember")
    @JsonBackReference
    private SpaceWallPermission spaceWallPermission;
}
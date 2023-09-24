package com.fastcampus.jober.domain.member.domain;

import com.fastcampus.jober.domain.BaseTimeEntity;
import com.fastcampus.jober.domain.spacewallmember.domain.SpaceWallMember;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "member")
@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Member extends BaseTimeEntity {

    @Id
    @Column(nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     *  양방향 참조 테스트
     */
    @OneToMany(mappedBy = "member")
    @JsonBackReference
    private List<SpaceWallMember> spaceWallMember;

    @Column(unique = true, nullable = false, length = 30)
    @JoinColumn(name = "email")
    private String email;

    @Column(nullable = false, length = 100)
    private String password;

    @Column(nullable = false, length = 10)
    private String username;

}

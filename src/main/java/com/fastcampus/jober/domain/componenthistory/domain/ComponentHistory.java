package com.fastcampus.jober.domain.componenthistory.domain;

import com.fastcampus.jober.domain.BaseTimeEntity;
import com.fastcampus.jober.domain.spacewallhistory.domain.SpaceWallHistory;
import com.fastcampus.jober.domain.template.domain.Template;
import com.fastcampus.jober.global.constant.Type;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "component_history")
@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class ComponentHistory extends BaseTimeEntity {

    @Id
    @Column(nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "temaplate_id")
    private Template template;

    @ManyToOne
    @JoinColumn(name = "space_wall_history_id")
    private SpaceWallHistory spaceWallHistory;

    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false)
    private Type type;

    @Column(nullable = false)
    private boolean visible;

    @Column(length = 1000)
    private String content;

    @Column(nullable = false)
    private int sequence;

    private Long spaceWallId;

}

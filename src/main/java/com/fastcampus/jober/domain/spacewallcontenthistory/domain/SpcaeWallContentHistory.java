package com.fastcampus.jober.domain.spacewallcontenthistory.domain;

import com.fastcampus.jober.domain.BaseTimeEntity;
import com.fastcampus.jober.domain.spacewallhistory.domain.SpaceWallHistory;
import com.fastcampus.jober.domain.template.domain.Template;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class SpcaeWallContentHistory extends BaseTimeEntity {

    @Id
    @Column(nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "temaplate_id")
    private Template template;

    @ManyToOne
    @JoinColumn(name = "space_wall_history_id", nullable = false)
    private SpaceWallHistory spaceWallHistory;

    @Column(nullable = false, length = 10)
    private String type;

    @Column(nullable = false)
    private boolean visible;

    @Column(length = 1000)
    private String content;

    @Column(nullable = false)
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int sequence;

    private Long spaceWallId;

}

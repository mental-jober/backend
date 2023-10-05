package com.fastcampus.jober.domain.space.component.componentTemp.domain;

import com.fastcampus.jober.domain.BaseTimeEntity;
import com.fastcampus.jober.domain.space.component.componentTemp.dto.ComponentTempRequest.ModifyDTOInSWT;
import com.fastcampus.jober.domain.space.spacewall.domain.SpaceWall;
import com.fastcampus.jober.domain.space.spacewall.spacewalltemp.domain.SpaceWallTemp;
import com.fastcampus.jober.domain.template.domain.Template;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class ComponentTemp extends BaseTimeEntity {

    @Id
    @Column(nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "parent_space_wall_temp_id")
    private SpaceWallTemp parentSpaceWallTemp;

    @ManyToOne
    @JoinColumn(name = "template_id")
    private Template template;

    @ManyToOne
    @JoinColumn(name = "this_space_wall_id")
    private SpaceWall thisSpaceWall;

    // 본래의 컴포넌트가 있는지 체크하기 위함
    private Long componentId;

    @Column(nullable = false, length = 10)
    private String type;

    @Column(nullable = false)
    private boolean visible;

    @Column(length = 100)
    private String title;

    @Column(length = 3000)
    private String content;

    @Column(nullable = false)
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int sequence;

    private boolean deleted;

    public void setTemplate(Template template) {
        this.template = template;
    }

    public void setThisSpaceWall(
        SpaceWall thisSpaceWall) {
        this.thisSpaceWall = thisSpaceWall;
    }

    public void setComponentId(Long componentId) {
        this.componentId = componentId;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setSequence(int sequence) {
        this.sequence = sequence;
    }

    public void updateInSWT(ModifyDTOInSWT modifyDTOInSWT) {
        if (this.sequence != modifyDTOInSWT.getSequence()) {
            this.sequence = modifyDTOInSWT.getSequence();
        }
        if (this.visible != modifyDTOInSWT.isVisible()) {
            this.visible = modifyDTOInSWT.isVisible();
        }
    }
}
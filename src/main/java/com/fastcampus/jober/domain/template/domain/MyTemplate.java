package com.fastcampus.jober.domain.template.domain;

import com.fastcampus.jober.domain.member.domain.Member;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import com.fastcampus.jober.domain.BaseTimeEntity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MyTemplate extends BaseTimeEntity {

    @Id
    @Column(nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany
    @JoinColumn(name = "temaplate_id", nullable = false)
    private List<Template> templates;

    @ManyToOne
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

}

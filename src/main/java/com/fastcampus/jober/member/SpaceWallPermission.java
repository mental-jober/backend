package com.fastcampus.jober.member;

import com.fastcampus.jober.spacewall.SpaceWall;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

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
    @PrimaryKeyJoinColumn
    private Member member;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private Type type; // WHITE / BLACK

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private Auths auths; // READ / EDIT / DELETE

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

    protected void onUpdate() { // 권한 수정 시 호출
        this.updatedAt = LocalDateTime.now();
    }
}

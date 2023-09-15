package com.fastcampus.jober.spacewall;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "space_wall")
@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class SpaceWall {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
}

package com.fastcampus.jober.domain.spacewall.repository;

import com.fastcampus.jober.domain.spacewall.domain.SpaceWall;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SpaceWallRepository extends JpaRepository<SpaceWall, Long> {

}

package com.fastcampus.jober.domain.component.repository;

import com.fastcampus.jober.domain.component.domain.Component;
import com.fastcampus.jober.domain.spacewall.domain.SpaceWall;
import java.util.List;

import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ComponentRepository extends JpaRepository<Component, Long> {

    @Query("select c from Component c where c.spaceWall = :spaceWall")
    List<Component> findComponentBySpaceWall(@Param("spaceWall") SpaceWall spaceWall);

}

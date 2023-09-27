package com.fastcampus.jober.domain.componenthistory.repository;

import com.fastcampus.jober.domain.componenthistory.domain.ComponentHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ComponentHistoryRepository extends JpaRepository<ComponentHistory, Long> {
}

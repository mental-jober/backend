package com.fastcampus.jober.domain.componenthistory.service;

import com.fastcampus.jober.domain.componenthistory.domain.ComponentHistory;
import com.fastcampus.jober.domain.componenthistory.repository.ComponentHistoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import static com.fastcampus.jober.domain.componenthistory.dto.ComponentHistoryRequest.*;
import static com.fastcampus.jober.domain.componenthistory.dto.ComponentHistoryResponse.ComponentHistoryResponseDTO;

@Service
@RequiredArgsConstructor
@Slf4j
public class ComponentHistoryService {

    private final ComponentHistoryRepository componentHistoryRepository;

}

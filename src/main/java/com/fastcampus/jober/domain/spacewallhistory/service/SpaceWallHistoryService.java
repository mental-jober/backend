package com.fastcampus.jober.domain.spacewallhistory.service;

import com.fastcampus.jober.domain.componenthistory.domain.ComponentHistory;
import com.fastcampus.jober.domain.componenthistory.dto.ComponentHistoryRequest;
import com.fastcampus.jober.domain.componenthistory.dto.ComponentHistoryResponse;
import com.fastcampus.jober.domain.componenthistory.repository.ComponentHistoryRepository;
import com.fastcampus.jober.domain.spacewallhistory.domain.SpaceWallHistory;
import com.fastcampus.jober.domain.spacewallhistory.dto.HistoryWrapper;
import com.fastcampus.jober.domain.spacewallhistory.dto.SpaceWallHistoryRequest;
import com.fastcampus.jober.domain.spacewallhistory.repository.SpaceWallHistoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.fastcampus.jober.domain.componenthistory.dto.ComponentHistoryRequest.*;
import static com.fastcampus.jober.domain.spacewallhistory.dto.HistoryWrapper.*;
import static com.fastcampus.jober.domain.spacewallhistory.dto.SpaceWallHistoryRequest.*;
import static com.fastcampus.jober.domain.spacewallhistory.dto.SpaceWallHistoryResponse.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class SpaceWallHistoryService {

    private final SpaceWallHistoryRepository spaceWallHistoryRepository;
    private final ComponentHistoryRepository componentHistoryRepository;

    public HistoryResponseDTOWrapper addSpaceWallHistory(HistoryRequestDTOWrapper requestDTO) {

        SpaceWallHistoryRequestDTO spaceWallHistoryRequest = requestDTO.getSpaceWallHistoryRequestDTO();

        SpaceWallHistory spaceWallHistory = SpaceWallHistory.builder()
                .spaceWallId(spaceWallHistoryRequest.getSpaceWallId())
                .url(spaceWallHistoryRequest.getUrl())
                .title(spaceWallHistoryRequest.getTitle())
                .description(spaceWallHistoryRequest.getDescription())
                .profileImageUrl(spaceWallHistoryRequest.getProfileImageUrl())
                .backgroundImageUrl(spaceWallHistoryRequest.getBackgroundImageUrl())
                .pathIds(spaceWallHistoryRequest.getPathIds())
                .authorized(spaceWallHistoryRequest.isAuthorized())
                .sequence(spaceWallHistoryRequest.getSequence())
                .build();

        SpaceWallHistoryResponseDTO spaceWallHistoryResponse =
                new SpaceWallHistoryResponseDTO(spaceWallHistoryRepository.save(spaceWallHistory));

        List<ComponentHistoryResponse.ComponentHistoryResponseDTO> componentHistoriesResponse = new ArrayList<>();
        for (ComponentHistoryRequestDTO componentHistoryRequest : requestDTO.getComponentHistoryRequestDTOs()) {
            ComponentHistory componentHistory = ComponentHistory.builder()
                    .templateId(componentHistoryRequest.getTemplateId())
                    .spaceWallHistoryId(spaceWallHistoryResponse.getId())
                    .type(componentHistoryRequest.getType())
                    .visible(componentHistoryRequest.isVisible())
                    .content(componentHistoryRequest.getContent())
                    .sequence(componentHistoryRequest.getSequence())
                    .spaceWallId(componentHistoryRequest.getSpaceWallId())
                    .build();

            componentHistoriesResponse.add(
                    new ComponentHistoryResponse
                            .ComponentHistoryResponseDTO(componentHistoryRepository.save(componentHistory))
            );
        }

        return new HistoryResponseDTOWrapper(spaceWallHistoryResponse, componentHistoriesResponse);
    }
}
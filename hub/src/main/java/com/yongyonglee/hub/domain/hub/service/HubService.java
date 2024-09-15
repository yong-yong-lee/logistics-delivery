package com.yongyonglee.hub.domain.hub.service;

import com.yongyonglee.hub.domain.hub.dto.request.CreateHubRequestDto;
import com.yongyonglee.hub.domain.hub.dto.request.UpdateHubRequestDto;
import com.yongyonglee.hub.domain.hub.dto.response.CreateHubResponseDto;
import com.yongyonglee.hub.domain.hub.dto.response.HubResponseDto;
import com.yongyonglee.hub.domain.hub.model.Hub;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface HubService {

    CreateHubResponseDto createHub(CreateHubRequestDto requestDto);

    HubResponseDto getHub(UUID hubId);

    Page<HubResponseDto> getHubs(Pageable pageable);

    Page<HubResponseDto> searchHubs(String hubName, String hubAddress, Pageable pageable);

    HubResponseDto updateHub(UUID hubId, UpdateHubRequestDto requestDto);

    void deleteHub(UUID hubId);

    Hub findById(UUID hubId);
}

package com.yongyonglee.hub.domain.hub.service;

import com.yongyonglee.hub.domain.hub.dto.request.CreateHubRequestDto;
import com.yongyonglee.hub.domain.hub.dto.response.CreateHubResponseDto;
import com.yongyonglee.hub.domain.hub.dto.response.HubResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface HubService {

    CreateHubResponseDto createHub(CreateHubRequestDto requestDto);

    HubResponseDto getHub(String hubId);

    Page<HubResponseDto> getHubs(Pageable pageable);

    Page<HubResponseDto> searchHubs(String hubName, String hubAddress, Pageable pageable);

    void deleteHub(String hubId);
}

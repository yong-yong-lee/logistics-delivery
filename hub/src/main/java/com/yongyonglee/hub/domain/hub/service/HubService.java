package com.yongyonglee.hub.domain.hub.service;

import com.yongyonglee.hub.domain.hub.dto.request.CreateHubRequestDto;
import com.yongyonglee.hub.domain.hub.dto.response.CreateHubResponseDto;

public interface HubService {

    CreateHubResponseDto createHub(CreateHubRequestDto requestDto);
}

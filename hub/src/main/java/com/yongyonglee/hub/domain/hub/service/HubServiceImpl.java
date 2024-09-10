package com.yongyonglee.hub.domain.hub.service;

import com.yongyonglee.hub.domain.hub.dto.request.CreateHubRequestDto;
import com.yongyonglee.hub.domain.hub.dto.response.CreateHubResponseDto;
import com.yongyonglee.hub.domain.hub.dto.response.HubResponseDto;
import com.yongyonglee.hub.domain.hub.exception.HubException;
import com.yongyonglee.hub.domain.hub.message.ExceptionMessage;
import com.yongyonglee.hub.domain.hub.model.Hub;
import com.yongyonglee.hub.domain.hub.repository.HubRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HubServiceImpl implements HubService {

    private final HubRepository hubRepository;

    @Override
    public CreateHubResponseDto createHub(CreateHubRequestDto requestDto) {

        Hub savedHub = hubRepository.save(Hub.of(requestDto));

        return CreateHubResponseDto.from(savedHub);
    }

    @Override
    public HubResponseDto getHub(String hubId) {

        Hub hub = findById(hubId);

        return HubResponseDto.from(hub);
    }

    @Override
    public Page<HubResponseDto> getHubs(Pageable pageable) {

        Page<Hub> hubs = hubRepository.findAllByIsDeletedFalse(pageable);

        return hubs.map(HubResponseDto::from);
    }

    public Hub findById(String hubId) {

        return hubRepository.findByIdAndIsDeletedFalse(hubId)
                .orElseThrow(() -> new HubException(ExceptionMessage.HUB_NOT_FOUND));
    }
}

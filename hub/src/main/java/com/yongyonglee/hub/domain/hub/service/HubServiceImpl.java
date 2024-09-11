package com.yongyonglee.hub.domain.hub.service;

import com.querydsl.core.BooleanBuilder;
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
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import static com.yongyonglee.hub.domain.hub.model.QHub.hub;

@Service
@RequiredArgsConstructor
@Transactional
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

    @Override
    public Page<HubResponseDto> searchHubs(String hubName, String hubAddress, Pageable pageable) {

        BooleanBuilder builder = new BooleanBuilder();

        // isDeleted가 false인 경우만 조회
        builder.and(hub.isDeleted.eq(false));

        if (StringUtils.hasText(hubName)) {
            builder.and(hub.hubName.containsIgnoreCase(hubName));
        }
        if (StringUtils.hasText(hubAddress)) {
            builder.and(hub.hubAddress.containsIgnoreCase(hubAddress));
        }

        Page<Hub> hubs = hubRepository.findAll(builder, pageable);

        return hubs.map(HubResponseDto::from);
    }

    @Override
    public void deleteHub(String hubId) {
        Hub hub = findById(hubId);

        // TODO: 사용자 정보 가져오기
        hub.deleteHub("userName");
    }

    public Hub findById(String hubId) {

        return hubRepository.findByIdAndIsDeletedFalse(hubId)
                .orElseThrow(() -> new HubException(ExceptionMessage.HUB_NOT_FOUND));
    }
}

package com.yongyonglee.hub.domain.hub.service;

import static com.yongyonglee.hub.domain.hub.model.QHub.hub;

import com.querydsl.core.BooleanBuilder;
import com.yongyonglee.hub.domain.hub.dto.request.CreateHubRequestDto;
import com.yongyonglee.hub.domain.hub.dto.request.UpdateHubRequestDto;
import com.yongyonglee.hub.domain.hub.dto.response.CreateHubResponseDto;
import com.yongyonglee.hub.domain.hub.dto.response.HubResponseDto;
import com.yongyonglee.hub.domain.hub.exception.HubException;
import com.yongyonglee.hub.domain.hub.message.ExceptionMessage;
import com.yongyonglee.hub.domain.hub.model.Hub;
import com.yongyonglee.hub.domain.hub.repository.HubRepository;
import com.yongyonglee.hub.domain.hub_route.service.HubRouteHelper;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

@Service
@RequiredArgsConstructor
@Transactional
public class HubServiceImpl implements HubService {

    private final HubRepository hubRepository;
    private final HubRouteHelper hubRouteHelper;

    @Override
    public CreateHubResponseDto createHub(CreateHubRequestDto requestDto) {

        Hub savedHub = hubRepository.save(Hub.of(requestDto));

        return CreateHubResponseDto.from(savedHub);
    }

    @Override
    public HubResponseDto getHub(UUID hubId) {

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
    public HubResponseDto updateHub(UUID hubId, UpdateHubRequestDto requestDto) {

        Hub hub = findById(hubId);

        // name 필드가 변경되었고, unique 조건이 있을 때 중복 검사 수행
        if(requestDto.hubName() != null && !requestDto.hubName().equals(hub.getHubName())) {

            if(hubRepository.existsByHubNameAndIsDeletedFalse(requestDto.hubName())){
                throw new HubException(ExceptionMessage.HUB_NAME_ALREADY_EXISTS);
            }
        }

        hub.updateHub(requestDto);

        return HubResponseDto.from(hub);
    }

    @Override
    public void deleteHub(UUID hubId) {
        Hub hub = findById(hubId);

        // TODO: 사용자 정보 가져오기
        hub.deleteHub("userName");

        // hub 삭제시, 해당 hub의 hubroute 정보도 삭제하는 로직 추가
        hubRouteHelper.deleteHubRoutesByHubId(hubId, "userName");

    }

    @Override
    public Hub findById(UUID hubId) {

        return hubRepository.findByIdAndIsDeletedFalse(hubId)
                .orElseThrow(() -> new HubException(ExceptionMessage.HUB_NOT_FOUND));
    }
}

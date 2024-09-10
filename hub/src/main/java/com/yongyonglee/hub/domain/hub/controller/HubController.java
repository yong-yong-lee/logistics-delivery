package com.yongyonglee.hub.domain.hub.controller;


import static com.yongyonglee.hub.domain.hub.message.SuccessMessage.CREATE_HUB_SUCCESS;
import static com.yongyonglee.hub.domain.hub.message.SuccessMessage.GET_HUB_SUCCESS;
import static com.yongyonglee.hub.global.response.SuccessResponse.success;

import com.yongyonglee.hub.domain.hub.dto.request.CreateHubRequestDto;
import com.yongyonglee.hub.domain.hub.service.HubService;
import com.yongyonglee.hub.global.aop.page.PageSizeLimit;
import com.yongyonglee.hub.global.response.CommonResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/hubs")
@RequiredArgsConstructor
public class HubController {

    private final HubService hubService;

    /**
     * 허브 생성 api
     */
    // TODO: 사용자 인증 및 인가(MASTER) 추가
    @PostMapping("")
    public ResponseEntity<? extends CommonResponse> createHub(@Valid @RequestBody CreateHubRequestDto requestDto) {

        return ResponseEntity.status(CREATE_HUB_SUCCESS.getHttpStatus())
                .body(success(CREATE_HUB_SUCCESS.getMessage(), hubService.createHub(requestDto)));
    }

    /**
     * 허브 단건 조회 api
     */
    // TODO: 사용자 인증 추가
    @GetMapping("/{hubId}")
    public ResponseEntity<? extends CommonResponse> getHub(@PathVariable(name = "hubId") String hubId) {

        return ResponseEntity.status(GET_HUB_SUCCESS.getHttpStatus())
                .body(success(GET_HUB_SUCCESS.getMessage(), hubService.getHub(hubId)));
    }

    /**
     * 허브 목록 조회 api
     */
    // TODO: 사용자 인증 추가
    @GetMapping("")
    @PageSizeLimit
    public ResponseEntity<? extends CommonResponse> getHubs(
            @PageableDefault(sort = "createdAt", direction = Direction.DESC) Pageable pageable) {

        return ResponseEntity.status(GET_HUB_SUCCESS.getHttpStatus())
                .body(success(GET_HUB_SUCCESS.getMessage(), hubService.getHubs(pageable)));
    }
}

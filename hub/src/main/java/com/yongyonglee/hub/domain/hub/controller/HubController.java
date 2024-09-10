package com.yongyonglee.hub.domain.hub.controller;


import static com.yongyonglee.hub.domain.hub.message.SuccessMessage.CREATE_HUB_SUCCESS;
import static com.yongyonglee.hub.global.response.SuccessResponse.success;

import com.yongyonglee.hub.domain.hub.dto.request.CreateHubRequestDto;
import com.yongyonglee.hub.domain.hub.service.HubService;
import com.yongyonglee.hub.global.response.CommonResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/hubs")
@RequiredArgsConstructor
public class HubController {

    private final HubService hubService;

    /** 허브 생성 api */
    // TODO: 사용자 인가(MASTER) 추가
    @PostMapping("")
    public ResponseEntity<? extends CommonResponse> createHub(
            @Valid @RequestBody CreateHubRequestDto requestDto) {

        return ResponseEntity.status(CREATE_HUB_SUCCESS.getHttpStatus())
                .body(success(CREATE_HUB_SUCCESS.getMessage(), hubService.createHub(requestDto)));
    }
}

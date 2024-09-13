package com.yongyonglee.hub.domain.hub.controller;

import static com.yongyonglee.hub.domain.hub.message.SuccessMessage.CREATE_HUB_SUCCESS;
import static com.yongyonglee.hub.domain.hub.message.SuccessMessage.DELETE_HUB_SUCCESS;
import static com.yongyonglee.hub.domain.hub.message.SuccessMessage.GET_HUB_SUCCESS;
import static com.yongyonglee.hub.domain.hub.message.SuccessMessage.SEARCH_HUB_SUCCESS;
import static com.yongyonglee.hub.domain.hub.message.SuccessMessage.UPDATE_HUB_SUCCESS;
import static com.yongyonglee.hub.global.response.SuccessResponse.success;

import com.yongyonglee.hub.domain.hub.dto.request.CreateHubRequestDto;
import com.yongyonglee.hub.domain.hub.dto.request.UpdateHubRequestDto;
import com.yongyonglee.hub.domain.hub.service.HubService;
import com.yongyonglee.hub.global.aop.page.PageSizeLimit;
import com.yongyonglee.hub.global.response.CommonResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/hubs")
@RequiredArgsConstructor
@Tag(name = "Hub-Controller", description = "/api/v1/hubs")
public class HubController {

    private final HubService hubService;

    /** 허브 생성 api */
    // TODO: 사용자 인증 및 인가(MASTER) 추가
    @PostMapping("")
    @Operation(summary = "허브 생성", description = "허브를 생성할 때 사용하는 API")
    public ResponseEntity<? extends CommonResponse> createHub(@Valid @RequestBody CreateHubRequestDto requestDto) {

        return ResponseEntity.status(CREATE_HUB_SUCCESS.getHttpStatus())
                .body(success(CREATE_HUB_SUCCESS.getMessage(), hubService.createHub(requestDto)));
    }

    /** 허브 단건 조회 api */
    // TODO: 사용자 인증 추가
    @GetMapping("/{hubId}")
    @Operation(summary = "허브 단건 조회", description = "허브를 단건으로 조회하는 API")
    public ResponseEntity<? extends CommonResponse> getHub(@PathVariable(name = "hubId") UUID hubId) {

        return ResponseEntity.status(GET_HUB_SUCCESS.getHttpStatus())
                .body(success(GET_HUB_SUCCESS.getMessage(), hubService.getHub(hubId)));
    }

    /** 허브 목록 조회 api */
    // TODO: 사용자 인증 추가
    @GetMapping("")
    @PageSizeLimit
    @Operation(summary = "허브 목록 조회", description = "허브 목록을 조회할 때 사용하는 API")
    public ResponseEntity<? extends CommonResponse> getHubs(
            @PageableDefault(sort = "createdAt", direction = Direction.DESC) Pageable pageable) {

        return ResponseEntity.status(GET_HUB_SUCCESS.getHttpStatus())
                .body(success(GET_HUB_SUCCESS.getMessage(), hubService.getHubs(pageable)));
    }

    /** 허브 검색 api(이름, 주소별) */
    // TODO: 사용자 인증 추가
    @GetMapping("/search")
    @PageSizeLimit
    @Operation(summary = "허브 검색", description = "허브 이름과 주소를 키워드로 검색할 때 사용하는 API")
    public ResponseEntity<? extends CommonResponse> searchHubs(
            @RequestParam(required = false) String hubName,
            @RequestParam(required = false) String hubAddress,
            @PageableDefault(sort = "createdAt", direction = Direction.DESC) Pageable pageable) {

        return ResponseEntity.status(SEARCH_HUB_SUCCESS.getHttpStatus())
                .body(success(SEARCH_HUB_SUCCESS.getMessage(), hubService.searchHubs(hubName, hubAddress, pageable)));
    }

    /** 허브  수정 api */
    // TODO: 사용자 인증 및 인가(MASTER) 추가
    @PutMapping("/{hubId}")
    @Operation(summary = "허브 수정", description = "허브 정보를 수정할 때 사용하는 API")
    public ResponseEntity<? extends CommonResponse> updateHub(@PathVariable(name = "hubId") UUID hubId,
            @RequestBody UpdateHubRequestDto requestDto) {

        return ResponseEntity.status(UPDATE_HUB_SUCCESS.getHttpStatus())
                .body(success(UPDATE_HUB_SUCCESS.getMessage(), hubService.updateHub(hubId, requestDto)));
    }

    /** 허브 삭제 api */
    // TODO: 사용자 인증 및 인가(MASTER) 추가
    @DeleteMapping("/{hubId}")
    @Operation(summary = "허브 삭제", description = "허브를 삭제할 때 사용하는 API")
    public ResponseEntity<? extends CommonResponse> deleteHub(@PathVariable(name = "hubId") UUID hubId) {

        hubService.deleteHub(hubId);

        return ResponseEntity.status(DELETE_HUB_SUCCESS.getHttpStatus())
                .body(success(DELETE_HUB_SUCCESS.getMessage()));
    }
}

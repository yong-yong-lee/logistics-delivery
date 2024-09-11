package com.yongyonglee.order.domain.route.service;

import com.yongyonglee.order.domain.order.dto.OrderCreateRequest;
import com.yongyonglee.order.domain.route.entity.Route;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class RouteService {

//    private final HubRouteRepository hubRouteRepository;

    public List<Route> addRoute(OrderCreateRequest orderCreateRequest) {

        //아마 vendor의 id로 알고 있습니다. 이 vendor가 속한 hub로 아래 start,end hub를 교체해야합니다.

//        UUID startHub = orderCreateRequest.getSupplyId();
//
//        int start = hubRouteRepository.findById(startHub).getlevel();
//
//        UUID endHub = orderCreateRequest.getDemandId();
//
//        int end = hubRouteRepository.findById(endHub).getlevel();

        List<Route> routeList = new ArrayList<>();
//        boolean isForward = start < end;
//
//        int currentHub = start;
//
//        while (currentHub != end) {
//            int nextHub = isForward ? currentHub + 1 : currentHub - 1;
//
//            // 현재 허브에서 다음 허브로 가는 경로를 찾기
//            List<HubRoute> nextRoutes = hubRouteRepository.findByStartHubAndEndHub(currentHub, nextHub);
//
//            if (nextRoutes.isEmpty()) {
//                throw new RuntimeException("경로를 찾을 수 없습니다.");
//            }
//
//            // 첫 번째 경로 선택 (필요시 더 나은 경로 선택 로직 추가 가능)
//            HubRoute nextRoute = nextRoutes.get(0);
//            routeList.add(nextRoute);
//
//            // 현재 허브를 다음 허브로 업데이트
//            currentHub = nextRoute.getEndHub();
//        }


        return routeList;

    }
}

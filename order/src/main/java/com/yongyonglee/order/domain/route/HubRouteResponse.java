package com.yongyonglee.order.domain.route;

import java.sql.Timestamp;
import java.util.UUID;

public class HubRouteResponse {

    public UUID getStartHub() {
        return UUID.randomUUID();
    }

    public UUID getEndHub() {
        return UUID.randomUUID();
    }

    public Integer getEstimatedDistance() {
        return 0;
    }

    public Timestamp getEstimatedTime() {
        return new Timestamp(1);
    }
//todo hubroute response 만들어지면 추가해야합니다. 현재 모두 dummy로 넣어놨어요.
}

package com.yongyonglee.vendor.global.auditor;

import java.util.Optional;
import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Service;

@Service
public class UserAuditorAware implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor() {

        // TODO: 유저 정보 수정하기
        String modifiedBy = "unknown";

        return Optional.of(modifiedBy);
    }
}

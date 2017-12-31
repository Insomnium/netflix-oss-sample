package com.epam.hw.netflix.repository;

import com.epam.hw.netflix.domain.CreditLimit;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;

import java.util.List;
import java.util.Map;


@ConfigurationProperties("limits")
@Getter
@Setter
@RefreshScope
public class DummyCreditLimits {
    private Map<String, CreditLimit> dummy;
}

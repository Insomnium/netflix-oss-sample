package com.epam.hw.netflix.repository;

import com.epam.hw.netflix.domain.Workspace;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;

import java.util.List;


@ConfigurationProperties("workspaces")
@Getter
@Setter
@RefreshScope
public class DummyWorkspaces {
    private List<Workspace> dummy;
}

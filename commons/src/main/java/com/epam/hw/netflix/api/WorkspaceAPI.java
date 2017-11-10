package com.epam.hw.netflix.api;

import com.epam.hw.netflix.domain.Workspace;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;


@FeignClient(name = "workspaces-api")
public interface WorkspaceAPI {

    @GetMapping(value = "/{id}", consumes = APPLICATION_JSON_VALUE)
    Workspace getWorkspaceById(@PathVariable("id") String id);
}

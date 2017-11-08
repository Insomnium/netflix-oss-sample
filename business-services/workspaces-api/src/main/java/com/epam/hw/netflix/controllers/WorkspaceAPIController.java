package com.epam.hw.netflix.controllers;

import com.epam.hw.netflix.api.WorkspaceAPI;
import com.epam.hw.netflix.domain.Workspace;
import com.epam.hw.netflix.services.WorkspaceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.epam.hw.netflix.controllers.WorkspaceAPIController.ROOT_URI;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;


@RestController
@RequestMapping(ROOT_URI)
@Slf4j
public class WorkspaceAPIController implements WorkspaceAPI {

    public static final String ROOT_URI = "/";
    public static final String WORKSPACE_ID_URI = "/{id}";

    @Autowired
    private WorkspaceService workspaceService;

    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public List<Workspace> getAllWorkspaces() {
        return workspaceService.getAll();
    }

    @GetMapping(value = WORKSPACE_ID_URI, produces = APPLICATION_JSON_VALUE)
    public Workspace getWorkspaceById(@PathVariable("id") String id) {
        log.info("Instance {} received workspace request", this);
        return workspaceService.findWorkspace(id);
    }
}

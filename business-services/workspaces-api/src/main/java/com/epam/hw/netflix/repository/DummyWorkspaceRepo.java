package com.epam.hw.netflix.repository;


import com.epam.hw.netflix.domain.Workspace;
import com.epam.hw.netflix.exceptions.NoWorkspaceFoundException;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor
public class DummyWorkspaceRepo implements WorkspacesRepo {

    private final DummyWorkspaces workspaces;

    @Override
    public List<Workspace> getAll() {
        return workspaces.getDummy();
    }

    @Override
    public Workspace findById(String id) {
        return workspaces.getDummy()
                .stream()
                .filter(w -> StringUtils.equals(w.getId(), id))
                .findFirst()
                .orElseThrow(() -> new NoWorkspaceFoundException(format("No workspace found with id: %s", id)));
    }
}

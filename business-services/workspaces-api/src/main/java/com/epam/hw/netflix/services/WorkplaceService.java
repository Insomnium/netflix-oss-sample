package com.epam.hw.netflix.services;

import com.epam.hw.netflix.domain.Workspace;
import com.epam.hw.netflix.exceptions.NoWorkspaceFoundException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.epam.hw.netflix.domain.OSFamily.LINUX;
import static com.epam.hw.netflix.domain.OSFamily.OSX;
import static com.epam.hw.netflix.domain.OSFamily.WINDOWS;
import static com.google.common.collect.Lists.newArrayList;
import static java.lang.String.format;
import static java.util.UUID.randomUUID;

@Service
public class WorkplaceService {

    private final List<Workspace> workspaces = newArrayList(
            new Workspace("0000001", 1, 1, randomUUID().toString(), WINDOWS),
            new Workspace("0000002", 1, 2, randomUUID().toString(), WINDOWS),
            new Workspace("0000003", 1, 3, randomUUID().toString(), WINDOWS),
            new Workspace("0000004", 1, 4, randomUUID().toString(), OSX),
            new Workspace("0000005", 1, 5, randomUUID().toString(), OSX),
            new Workspace("0000006", 1, 6, randomUUID().toString(), OSX),
            new Workspace("0000007", 1, 7, randomUUID().toString(), WINDOWS),
            new Workspace("0000008", 2, 1, randomUUID().toString(), WINDOWS),
            new Workspace("0000009", 2, 2, randomUUID().toString(), WINDOWS),
            new Workspace("0000010", 2, 3, randomUUID().toString(), OSX),
            new Workspace("0000011", 2, 4, randomUUID().toString(), OSX),
            new Workspace("0000012", 2, 5, randomUUID().toString(), WINDOWS),
            new Workspace("0000013", 2, 6, randomUUID().toString(), WINDOWS),
            new Workspace("0000014", 2, 7, randomUUID().toString(), LINUX),
            new Workspace("0000015", 2, 9, randomUUID().toString(), LINUX)
    );

    public Workspace findWorkspace(String id) {
        return workspaces.stream()
                .filter(w -> StringUtils.equals(w.getId(), id))
                .findFirst()
                .orElseThrow(() -> new NoWorkspaceFoundException(format("No workspace found with id: %s", id)));
    }
}

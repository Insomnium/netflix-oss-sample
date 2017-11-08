package com.epam.hw.netflix.services;

import com.epam.hw.netflix.domain.Workspace;
import com.epam.hw.netflix.repository.WorkspacesRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WorkspaceService {

    private final WorkspacesRepo workspacesRepo;

    public List<Workspace> getAll() {
        return workspacesRepo.getAll();
    }

    public Workspace findWorkspace(String id) {
        return workspacesRepo.findById(id);
    }
}

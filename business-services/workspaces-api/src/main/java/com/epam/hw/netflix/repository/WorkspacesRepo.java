package com.epam.hw.netflix.repository;


import com.epam.hw.netflix.domain.Workspace;

import java.util.List;

public interface WorkspacesRepo {
    List<Workspace> getAll();
    Workspace findById(String id);
}

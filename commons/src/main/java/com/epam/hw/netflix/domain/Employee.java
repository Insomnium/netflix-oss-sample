package com.epam.hw.netflix.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;


@Data
@AllArgsConstructor
@Accessors(chain = true)
public class Employee {
    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private String workspaceId;
}

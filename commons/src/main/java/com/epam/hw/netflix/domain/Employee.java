package com.epam.hw.netflix.domain;

import lombok.*;
import lombok.experimental.Accessors;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class Employee {
    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private String workspaceId;
}

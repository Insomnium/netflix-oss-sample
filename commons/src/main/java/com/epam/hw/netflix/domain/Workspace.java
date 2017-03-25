package com.epam.hw.netflix.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class Workspace {
    private String id;
    private int unit;
    private int seat;
    private String serialNumber;
    private OSFamily osFamily;
}

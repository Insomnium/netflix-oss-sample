package net.ins.hw.netflix.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Limit {
    private String cardNumber;
    private String currencyCode;
    private Long limit;
}

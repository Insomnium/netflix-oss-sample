package net.ins.hw.netflix.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.UUID;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Accessors(chain = true)
public class Order {
    private UUID id;
    private String ownerId;
    private String cardNumber;
    private Amount amount;
    private OrderStatus status;
}

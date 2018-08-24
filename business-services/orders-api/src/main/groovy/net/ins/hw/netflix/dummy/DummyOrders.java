package net.ins.hw.netflix.dummy;


import lombok.Data;
import net.ins.hw.netflix.domain.Order;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

@Data
@ConfigurationProperties("dummy")
public class DummyOrders {
    private List<Order> orders;
}

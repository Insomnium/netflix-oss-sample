package net.ins.hw.netflix.dummy;

import lombok.Data;
import net.ins.hw.netflix.domain.Limit;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

@Data
@ConfigurationProperties("dummy")
public class DummyLimits {
    private List<Limit> limits;
}

package net.ins.hw.netflix.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

@Data
@ConfigurationProperties("allowed")
public class AuthApplicationProperties {
    private List<String> origins;
}

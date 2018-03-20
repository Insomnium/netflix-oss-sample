package net.ins.hw.netflix.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

/**
 * @deprecated Try to use <tt>endpoints.cors.*</tt> instead
 */
@Deprecated
@Data
@ConfigurationProperties("authorized")
public class AuthApplicationProperties {
    private List<String> origins;
}

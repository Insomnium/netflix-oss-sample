package net.ins.hw.netflix.domain;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

import static java.time.LocalDateTime.now;

/**
 * @author Ins137@gmail.com
 * Created at 1/29/2018
 */
@Data
@Builder
public class Info {
    private LocalDateTime dateTime;
    private String os;
    private String uuid;

    public static Info info() {
        return Info.builder()
                .dateTime(now())
                .os(System.getenv("os.name"))
                .uuid(UUID.randomUUID().toString())
                .build();
    }
}

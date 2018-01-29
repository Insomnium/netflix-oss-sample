package net.ins.hw.netflix.controllers;

import net.ins.hw.netflix.domain.Info;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Ins137@gmail.com
 * Created at 1/29/2018
 */
@RestController
public class StandaloneAppController {

    @GetMapping("/info")
    public Info getInfo() {
        return Info.info();
    }
}

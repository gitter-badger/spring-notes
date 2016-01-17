package cz.sycha.inventory.cz.sycha.inventory.controllers;

import cz.sycha.inventory.cz.sycha.inventory.models.Index;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;

@RestController
public class IndexController {
    private final AtomicLong counter = new AtomicLong();

    @RequestMapping("/")
    public Index index() {
        return new Index(counter.incrementAndGet());
    }
}

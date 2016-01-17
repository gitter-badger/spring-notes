package cz.sycha.notes.controllers;

import cz.sycha.notes.models.Index;
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

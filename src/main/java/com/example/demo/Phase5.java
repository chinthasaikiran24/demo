package com.example.demo;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import service.Phase5Service5;

@RestController
@RequestMapping("/Phase5")
public class Phase5 {

    private final Phase5Service5 phase5Service5;

    public Phase5(Phase5Service5 phase5Service5) {
        this.phase5Service5 = phase5Service5;
    }

    @PostMapping("/obj")
    public String call(@RequestBody String xml) throws Exception {

        return phase5Service5.convertXml(xml);
    }
}
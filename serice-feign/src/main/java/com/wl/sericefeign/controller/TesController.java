package com.wl.sericefeign.controller;

import com.wl.sericefeign.feign.SchedualServiceTes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TesController {

    @Autowired
    private SchedualServiceTes schedualServiceTes;

    @GetMapping(value = "/hi")
    public String tes(@RequestParam String name) {
        return schedualServiceTes.tesClientOne(name);
    }
}

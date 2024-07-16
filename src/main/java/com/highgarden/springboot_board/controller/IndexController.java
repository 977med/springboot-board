package com.highgarden.springboot_board.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Slf4j
public class IndexController {
    @Value("${jasypt.encryptor.password}")
    private String key;

    @GetMapping("/")
    public String index() throws Exception{
        log.info("index메서드 call");
        log.info("key : "+key);
        return "index";
    }
}

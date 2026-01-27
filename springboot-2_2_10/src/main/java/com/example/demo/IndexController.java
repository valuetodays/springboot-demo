package com.example.demo;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * .
 *
 * @author lei.liu
 * @since 2025-12-19
 */
@RestController
public class IndexController {
    @RequestMapping("/")
    public String index(){
        return "hello world";
    }
}

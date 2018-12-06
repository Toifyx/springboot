package com.toi.demon.control;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @auth Toifyx
 * @date 2018/12/5
 */
@RestController
public class Control
{
    @RequestMapping("/hello")
    @ResponseBody
    String home() {
        return "Hello ,spring boot!";
    }
}

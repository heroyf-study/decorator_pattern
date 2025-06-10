package com.heroyf.tech.insight.spring;

import java.util.Map;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author frankfyang
 * @date 2025/6/9 16:36
 */
@RestController
@RequestMapping("/api")
public class MyController {

    @PostMapping("/origin")
    public Map<String, Object> origin(@TimestampRequestBody Map<String, Object> json) {
        return json;
    }
}

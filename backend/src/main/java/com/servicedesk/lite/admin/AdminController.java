package com.servicedesk.lite.admin;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class AdminController {

    @GetMapping("/admin/ping")
    public Map<String, Object> ping() {
        return Map.of("ok", true, "message", "admin access granted");
    }
}

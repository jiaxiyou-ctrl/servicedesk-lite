package com.servicedesk.lite.ticket;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class TicketController {

    // GET /tickets：任何登录用户可访问（由 SecurityConfig 控制）
    @GetMapping("/tickets")
    public List<Map<String, Object>> list() {
        return List.of(
                Map.of("id", 1, "title", "Printer not working"),
                Map.of("id", 2, "title", "VPN access request")
        );
    }

    // POST /tickets：仅 ADMIN（由 SecurityConfig 控制）
    @PostMapping("/tickets")
    public Map<String, Object> create(@RequestBody Map<String, Object> body) {
        return Map.of(
                "created", true,
                "ticket", body
        );
    }

    // DELETE /tickets/{id}：仅 ADMIN（由 SecurityConfig 控制）
    @DeleteMapping("/tickets/{id}")
    public Map<String, Object> delete(@PathVariable int id) {
        return Map.of("deleted", true, "id", id);
    }
}

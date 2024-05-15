package cn.az.code.img.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author haru
 * @date 2024-05-15 22:28
 */
@RestController
@RequestMapping("/api/v1/echo")
public class EchoController {

    @GetMapping
    ResponseEntity<Map<String, String>> echo(@RequestParam @RequestBody Map<String, String> map) {
        return ResponseEntity.ok(map);
    }
}

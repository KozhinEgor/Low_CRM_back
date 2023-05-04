package com.crm.low_crm.controller;

import com.crm.low_crm.model.dto.AsyncResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class AsyncController {
    private ConcurrentMap<UUID, AsyncResponseDto> result = new ConcurrentHashMap<>();
    public void set(UUID key, AsyncResponseDto value) {
        this.result.put(key, value);
    }

    @GetMapping
    public ResponseEntity<AsyncResponseDto> get(@RequestParam UUID key) {
        if (this.result.containsKey(key) && result.get(key).getPercent() == 100){
            return ResponseEntity.ok(this.result.remove(key));
        }
        else if(this.result.containsKey(key) ){
            return ResponseEntity.ok(this.result.get(key));
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }

}

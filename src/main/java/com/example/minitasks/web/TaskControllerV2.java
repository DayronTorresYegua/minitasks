package com.example.minitasks.web;

import com.example.minitasks.dto.CreateTaskDTO;
import com.example.minitasks.model.SimpleTask;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping("/v2")
public class TaskControllerV2 {

    private final List<SimpleTask> inMemory = new ArrayList<>();
    private final AtomicLong seq = new AtomicLong(0);

    @GetMapping("/tasks")
    public ResponseEntity<List<SimpleTask>> list() {
        return ResponseEntity.ok(inMemory);
    }

    @PostMapping("/tasks")
    public ResponseEntity<SimpleTask> create(@RequestBody CreateTaskDTO dto) {
        SimpleTask task = new SimpleTask(seq.incrementAndGet(), dto.title(), false);
        inMemory.add(task);
        return ResponseEntity.created(URI.create("/v2/tasks/" + task.getId())).body(task);
    }
}

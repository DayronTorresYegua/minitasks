package com.example.minitasks.web;

import com.example.minitasks.dto.CreateTaskDTO;
import com.example.minitasks.model.SimpleTask;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping("/v1")
public class TaskControllerV1 {

    private final List<SimpleTask> inMemory = new ArrayList<>();
    private final AtomicLong seq = new AtomicLong(0);

    @GetMapping("/tasks")
    public List<SimpleTask> list() {
        return inMemory;
    }

    @PostMapping("/tasks")
    @ResponseStatus(HttpStatus.CREATED)
    public SimpleTask create(@RequestBody CreateTaskDTO dto) {
        SimpleTask task = new SimpleTask(seq.incrementAndGet(), dto.title(), false);
        inMemory.add(task);
        return task;
    }

    @GetMapping("/tasks/{id}")
    public ResponseEntity<SimpleTask> get(@PathVariable Long id) {
        return inMemory.stream()
                .filter(task -> Objects.equals(task.getId(), id))
                .findFirst()
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}

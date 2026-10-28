package com.example.minitasks.web;

import com.example.minitasks.dto.CreateTaskDTO;
import com.example.minitasks.dto.TaskResponseDTO;
import com.example.minitasks.services.TaskService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/v4/tasks")
public class TaskControllerV4 {

    private final TaskService service;

    public TaskControllerV4(TaskService service) {
        this.service = service;
    }

    @GetMapping
    public List<TaskResponseDTO> list() {
        return service.list();
    }

    @GetMapping("/{id}")
    public TaskResponseDTO get(@PathVariable Long id) {
        return service.get(id);
    }

    @PostMapping
    public ResponseEntity<TaskResponseDTO> create(@RequestBody CreateTaskDTO dto) {
        TaskResponseDTO created = service.create(dto);
        return ResponseEntity.created(URI.create("/v4/tasks/" + created.id())).body(created);
    }
}

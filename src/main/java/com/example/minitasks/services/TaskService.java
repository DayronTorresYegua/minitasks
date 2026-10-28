package com.example.minitasks.services;

import com.example.minitasks.dto.CreateTaskDTO;
import com.example.minitasks.dto.TaskResponseDTO;
import com.example.minitasks.entities.Task;
import com.example.minitasks.exceptions.TaskNotFoundException;
import com.example.minitasks.repositories.TaskRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class TaskService {

    private final TaskRepository repo;

    public TaskService(TaskRepository repo) {
        this.repo = repo;
    }

    @Transactional(readOnly = true)
    public List<TaskResponseDTO> list() {
        return repo.findAll().stream()
                .map(TaskResponseDTO::from)
                .toList();
    }

    @Transactional(readOnly = true)
    public TaskResponseDTO get(Long id) {
        return repo.findById(id)
                .map(TaskResponseDTO::from)
                .orElseThrow(() -> new TaskNotFoundException(id));
    }

    public TaskResponseDTO create(CreateTaskDTO dto) {
        Task task = new Task(dto.title().strip());
        task.setCompleted(false);
        return TaskResponseDTO.from(repo.save(task));
    }
}

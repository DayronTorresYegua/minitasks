package com.example.minitasks.services;

import com.example.minitasks.dto.CreateTaskDTO;
import com.example.minitasks.dto.TaskResponseDTO;
import com.example.minitasks.entities.Task;
import com.example.minitasks.repositories.TaskRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TaskServiceTest {

    @Mock
    TaskRepository repo;

    @InjectMocks
    TaskService service;

    @Test
    void createStripsTitleEdges() {
        when(repo.save(any(Task.class))).thenAnswer(call -> call.getArgument(0));

        TaskResponseDTO result = service.create(new CreateTaskDTO("  Repasar Spring Boot  "));

        assertThat(result.title()).isEqualTo("Repasar Spring Boot");
        assertThat(result.done()).isFalse();
    }

    @Test
    void createKeepsInnerSpaces() {
        when(repo.save(any(Task.class))).thenAnswer(call -> call.getArgument(0));

        TaskResponseDTO result = service.create(new CreateTaskDTO("Repasar  Spring Boot"));

        assertThat(result.title()).isEqualTo("Repasar  Spring Boot");
        assertThat(result.done()).isFalse();
    }
}

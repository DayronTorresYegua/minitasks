package com.example.minitasks.web;

import com.example.minitasks.dto.TaskResponseDTO;
import com.example.minitasks.exceptions.TaskNotFoundException;
import com.example.minitasks.services.TaskService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(TaskControllerV4.class)
class TaskControllerV4Test {

    @Autowired
    MockMvc mvc;

    @MockitoBean
    TaskService service;

    @Test
    void createsTaskThroughService() throws Exception {
        when(service.create(any())).thenReturn(new TaskResponseDTO(99L, "Recortada", false));

        mvc.perform(post("/v4/tasks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"title\":\"  Recortada  \"}"))
                .andExpect(status().isCreated())
                .andExpect(header().string("Location", "/v4/tasks/99"))
                .andExpect(jsonPath("$.id").value(99))
                .andExpect(jsonPath("$.title").value("Recortada"))
                .andExpect(jsonPath("$.done").value(false));
    }

    @Test
    void listsTasks() throws Exception {
        when(service.list()).thenReturn(List.of(new TaskResponseDTO(1L, "Leer README", false)));

        mvc.perform(get("/v4/tasks"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].title").value("Leer README"))
                .andExpect(jsonPath("$[0].done").value(false));
    }

    @Test
    void missingTaskReturns404() throws Exception {
        when(service.get(9999L)).thenThrow(new TaskNotFoundException(9999L));

        mvc.perform(get("/v4/tasks/9999"))
                .andExpect(status().isNotFound());
    }
}

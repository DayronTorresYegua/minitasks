package com.example.minitasks.dto;

import com.example.minitasks.entities.Task;

/**
 * Representación pública de una tarea.
 * La entidad guarda la columna {@code completed}; hacia fuera se expone como
 * {@code done}, que es el nombre del contrato de la práctica.
 */
public record TaskResponseDTO(Long id, String title, boolean done) {

    public static TaskResponseDTO from(Task task) {
        return new TaskResponseDTO(task.getId(), task.getTitle(), task.isCompleted());
    }
}

package com.example.minitasks.dto;

/**
 * Datos que llegan en el cuerpo de un POST /tasks.
 * Solo el título: el id y el estado los decide el servidor.
 */
public record CreateTaskDTO(String title) {
}

package com.example.minitasks.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * La capa de servicio avisa con esta excepción de que el id no existe.
 * El mapeo a HTTP vive aquí y no en el servicio.
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class TaskNotFoundException extends RuntimeException {

    public TaskNotFoundException(Long id) {
        super("Task not found: " + id);
    }
}

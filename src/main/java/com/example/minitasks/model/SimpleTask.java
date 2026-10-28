package com.example.minitasks.model;

/**
 * Tarea en memoria para las versiones V1 y V2 del controlador.
 * Todavía no toca la base de datos: por eso es un POJO y no una entidad JPA.
 */
public class SimpleTask {

    private Long id;
    private String title;
    private boolean done;

    public SimpleTask() {
    }

    public SimpleTask(Long id, String title, boolean done) {
        this.id = id;
        this.title = title;
        this.done = done;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }
}

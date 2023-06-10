package br.com.logiquesistemas.task.service;

import br.com.logiquesistemas.task.domain.entities.Task;
import br.com.logiquesistemas.task.domain.repository.TaskRepo;

public record CreateTask(TaskRepo repo) {
    
    public void execute(InsertTask insert){
        Task task = new Task(insert.title, insert.description);
        repo.create(task);
    }

    public record InsertTask(
        String title,
        String description
    ){}

    public record OutTask(
        String id,
        String title,
        String description,
        String task
    ){}

}

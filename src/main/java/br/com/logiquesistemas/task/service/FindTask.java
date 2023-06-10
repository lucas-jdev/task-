package br.com.logiquesistemas.task.service;

import java.util.Collection;

import br.com.logiquesistemas.task.domain.entities.Task;
import br.com.logiquesistemas.task.domain.error.TaskNotFound;
import br.com.logiquesistemas.task.domain.repository.TaskRepo;

public record FindTask(TaskRepo repo) {
    
    public Task byId(String id) throws TaskNotFound{
        return repo.findById(id).orElseThrow(() -> new TaskNotFound());
    }
    
    public Collection<Task> all(){
        return repo.findAll();
    }

}

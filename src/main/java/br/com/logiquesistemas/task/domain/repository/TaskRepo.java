package br.com.logiquesistemas.task.domain.repository;

import java.util.Collection;
import java.util.Optional;

import br.com.logiquesistemas.task.domain.entities.Task;

public interface TaskRepo {
    
    void create(Task task);

    void update(Task task);

    void deleteById(String id);

    void deleteAll();

    Collection<Task> findAll();

    Optional<Task> findById(String id);

}

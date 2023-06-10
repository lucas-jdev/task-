package br.com.logiquesistemas.task.domain.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.logiquesistemas.task.domain.entities.Task;

public interface TaskJPA extends JpaRepository<Task,UUID> {
    
}

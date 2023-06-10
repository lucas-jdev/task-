package br.com.logiquesistemas.task.service;

import br.com.logiquesistemas.task.domain.repository.TaskRepo;

public record DeleteTask(TaskRepo repo) {

    public void byId(String id) {
        repo.deleteById(id);
    }

}

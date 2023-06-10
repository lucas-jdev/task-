package br.com.logiquesistemas.task.service;

import br.com.logiquesistemas.task.domain.entities.Task;
import br.com.logiquesistemas.task.domain.repository.TaskRepo;

public record UpdateTask(TaskRepo repo) {
    
    public void execute(final InnerTaskServiceDTO dto) {
        var task = repo.findById(dto.id);
        if (task.isPresent()){
            Task taskUpdate = task.get();
            validateInputsToChangeAttributes(dto, taskUpdate);
            repo.update(taskUpdate);
        }
    }

    private void validateInputsToChangeAttributes(InnerTaskServiceDTO dto, Task taskUpdate) {
        if (dto.title() != null)
            taskUpdate.changeTitle(dto.title());
        if (dto.description() != null)
            taskUpdate.changeDescription(dto.description());
        if (dto.status() != null)
            taskUpdate.changeStatus(dto.status());
    }
    public record InnerTaskServiceDTO(
        String id,
        String title, 
        String description,
        String status
    ) {}

}

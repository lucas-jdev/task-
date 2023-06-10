package br.com.logiquesistemas.task.api;

import java.util.Collection;
import java.util.List;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.logiquesistemas.task.domain.error.TaskNotFound;
import br.com.logiquesistemas.task.domain.repository.TaskRepo;
import br.com.logiquesistemas.task.service.CreateTask;
import br.com.logiquesistemas.task.service.DeleteTask;
import br.com.logiquesistemas.task.service.FindTask;
import br.com.logiquesistemas.task.service.UpdateTask;
import br.com.logiquesistemas.task.service.CreateTask.InsertTask;
import br.com.logiquesistemas.task.service.UpdateTask.InnerTaskServiceDTO;

@RestController
@RequestMapping("/api/task")
public class TaskController {

    private final TaskRepo REPO;

    public TaskController(TaskRepo repo) {
        REPO = repo;
    }

    @PostMapping
    public ResponseEntity<Void> save(@RequestBody InnerTaskDTO dto){
        var createTask = new CreateTask(REPO);
        createTask.execute(new InsertTask(dto.title,dto.description));

        return ResponseEntity.status(HttpStatusCode.valueOf(201)).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<OutTaskDTO> findById(@PathVariable String id) throws TaskNotFound{
        var findTask = new FindTask(REPO);
        var task = findTask.byId(id);
        return ResponseEntity.ok(
            new OutTaskDTO(
                task.id(), 
                task.title(), 
                task.description(), 
                task.status().toString())
        );
    }

    @GetMapping
    public ResponseEntity<Collection<OutTaskDTO>> findAll(){
        var findTask = new FindTask(REPO);
        List<OutTaskDTO> result = findTask.all()
                                        .stream()
                                        .map(obj -> 
                                            new OutTaskDTO(
                                                obj.id(), 
                                                obj.title(), 
                                                obj.description(), 
                                                obj.status().toString())
                                        ).toList();
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable String id) throws TaskNotFound{
        var deleteTask = new DeleteTask(REPO);
        deleteTask.byId(id);

        return ResponseEntity.ok().build();
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Void> updateTask(@PathVariable String id,@RequestBody UpdateTaskDTO dto)
        throws IllegalArgumentException{
        var updateTask = new UpdateTask(REPO);
        updateTask.execute(
            new InnerTaskServiceDTO(id, dto.title, dto.description, dto.status));

        return ResponseEntity.ok().build();
    }

    public record InnerTaskDTO(
        String title,
        String description
    ) {}

    public record UpdateTaskDTO(
        String title,
        String description,
        String status
    ){}

    public record OutTaskDTO(
        String id,
        String title,
        String description,
        String status
    ){}

}

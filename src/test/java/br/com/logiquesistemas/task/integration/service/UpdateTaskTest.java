package br.com.logiquesistemas.task.integration.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.logiquesistemas.task.TaskApplication;
import br.com.logiquesistemas.task.domain.entities.StatusTask;
import br.com.logiquesistemas.task.domain.entities.Task;
import br.com.logiquesistemas.task.domain.error.ParamInvalid;
import br.com.logiquesistemas.task.domain.repository.TaskRepo;
import br.com.logiquesistemas.task.service.UpdateTask;
import br.com.logiquesistemas.task.service.UpdateTask.InnerTaskServiceDTO;

@SpringBootTest(classes = TaskApplication.class)
@TestInstance(Lifecycle.PER_CLASS)
class UpdateTaskTest {

    @Autowired
    private TaskRepo repo;

    private final String TITLE = "Imersão Code Level";
    private final String DESCRIPTION = "Evento de desenvolvimento que ocorrerá no meet";

    private Task task;

    @BeforeAll
    void setup() throws ParamInvalid {
        task = new Task(TITLE, DESCRIPTION);
        repo.create(task);
    }

    @AfterAll
    void tearDown() {
        repo.deleteAll();
    }

    @Test
    @DisplayName("Deve atualizar uma task no banco")
    void testUpdateTaskInDB() throws ParamInvalid {
        var updateTask = new UpdateTask(repo);

        task.changeStatus(StatusTask.IN_PROGRESS.toString());

        updateTask.execute(new InnerTaskServiceDTO(task.id(), task.title(), task.description(), task.status().toString()));

        var resultUpdate = repo.findById(task.id());
        assertEquals(task.status(), resultUpdate.get().status());
    }

}

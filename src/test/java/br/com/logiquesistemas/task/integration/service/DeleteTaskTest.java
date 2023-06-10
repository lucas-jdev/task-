package br.com.logiquesistemas.task.integration.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Collection;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.logiquesistemas.task.TaskApplication;
import br.com.logiquesistemas.task.domain.entities.Task;
import br.com.logiquesistemas.task.domain.repository.TaskRepo;
import br.com.logiquesistemas.task.service.DeleteTask;

@SpringBootTest(classes = TaskApplication.class)
@TestInstance(Lifecycle.PER_CLASS)
class DeleteTaskTest {
    
    @Autowired
    private TaskRepo repo;

    private final String TITLE = "Imersão Code Level";
    private final String DESCRIPTION = "Evento de desenvolvimento que ocorrerá no meet";

    private Task task;

    @BeforeAll
    void setup(){
        task = new Task(TITLE, DESCRIPTION);
        repo.create(task);
    }

    @AfterAll
    void tearDown(){
        repo.deleteAll();
    }

    @Test
    @DisplayName("Deve deletar uma task no banco")
    void testDeleteTaskInDB(){
        var deleteTask = new DeleteTask(repo);
        deleteTask.byId(task.id());

        Collection<Task> result = repo.findAll();
        assertEquals(0, result.size());
    }

}

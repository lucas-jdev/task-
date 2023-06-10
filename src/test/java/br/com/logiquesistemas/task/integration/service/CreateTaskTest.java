package br.com.logiquesistemas.task.integration.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.logiquesistemas.task.TaskApplication;
import br.com.logiquesistemas.task.domain.entities.Task;
import br.com.logiquesistemas.task.domain.repository.TaskRepo;

@SpringBootTest(classes = TaskApplication.class)
@TestInstance(Lifecycle.PER_CLASS)
class CreateTaskTest {
    
    @Autowired
    private TaskRepo repo;

    private final String TITLE = "Imersão Code Level";
    private final String DESCRIPTION = "Evento de desenvolvimento que ocorrerá no meet";

    @AfterAll
    void tearDown(){
        repo.deleteAll();
    }

    @Test
    @DisplayName("Deve salvar uma task no banco")
    void testSaveTaskInDB(){
        var task = new Task(TITLE, DESCRIPTION);
        repo.create(task);

        var result = repo.findAll();
        assertEquals(1, result.size());
    }

}

package br.com.logiquesistemas.task.integration.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

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
import br.com.logiquesistemas.task.domain.error.TaskNotFound;
import br.com.logiquesistemas.task.domain.repository.TaskRepo;
import br.com.logiquesistemas.task.service.FindTask;

@SpringBootTest(classes = TaskApplication.class)
@TestInstance(Lifecycle.PER_CLASS)
class FindTaskTest {
    
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
    @DisplayName("Deve buscar uma task no banco pelo id")
    void testFindTaskById() throws TaskNotFound{
        var findTask = new FindTask(repo);
        Task result = findTask.byId(task.id());
        assertNotNull(result);
    }

    @Test
    @DisplayName("Deve buscar todas as tasks no banco")
    void testFindAllTask(){
        var findTask = new FindTask(repo);
        Collection<Task> result = findTask.all();
        assertEquals(2, result.size());
    }

}

package br.com.taskManager.controller;

import br.com.taskManager.entity.Task;
import br.com.taskManager.service.TaskService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;



import static org.mockito.Mockito.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;



@WebMvcTest(TaskController.class)
@ExtendWith(MockitoExtension.class)

public class TaskControllerTest {

    //@InjectMocks
    //TaskController taskController;

    @MockBean
    TaskService taskService;

    @Autowired
    MockMvc mockMvc;
    Task task,task2, task3;
    ObjectMapper objectMapper;

    @BeforeEach
    public void setup(){
        task = new Task("Estudar", "Estudar para a prova", "12/04/24");
        task2 = new Task("Passear", "Passear o dia inteiro", "10/02/24");
        task3 = new Task("Comprar tal coisa", "Comprar tal coisa tananam", "05/08/25");
        task.setId(Integer.toUnsignedLong(1));
        objectMapper = new ObjectMapper();
    }

    @Test
    public void methodGetListShouldAcceptTheRequestAndCallServiceFindAll() throws Exception{
        when(taskService.findAll()).thenReturn(Collections.singletonList(task));

        mockMvc.perform(get("/task/list"))
                .andExpect(status().isOk())
                .andReturn();

        verify(taskService).findAll();
        verifyNoMoreInteractions(taskService);
    }

    @Test
    public void methodGetFormAddShouldAcceptTheRequest() throws Exception{
        mockMvc.perform(get("/task/form/add"))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    public void methodSaveTaskShouldAcceptTheRequestAndCallServiceSave() throws Exception{
        doNothing().when(taskService).save(new Task(task.getTitle(),task.getDescription(),task.getExpiration()));

        mockMvc
            .perform(post("/task/form/save")
                .param("title", task.getTitle())
                .param("description", task.getDescription())
                .param("expiration", task.getExpiration()))
            .andExpect(status().is3xxRedirection())
            .andExpect(header().string("Location","/task/list"));
    }

    @Test
    public void methodGetEditShouldAcceptTheRequestAndCallServiceFindById() throws Exception{
        when(taskService.findById(task.getId())).thenReturn(task);

        mockMvc.perform(get("/edit/{id}", task.getId())
                .param("title", task.getTitle())
                .param("description", task.getDescription())
                .param("expiration", task.getExpiration()))
                .andExpect(status().isOk());
    }

    @Test
    public void methodDeleteShouldAcceptTheRequestAndCallServiceDelete() throws Exception{
        doNothing().when(taskService).delete(task.getId());

        mockMvc.perform(get("/delete/{id}", task.getId()))
                .andExpect(status().is3xxRedirection());
    }

    @Test
    public void methodSortByDataShouldReturnATaskList() throws ParseException {
        TaskController taskController = new TaskController();
        List<Task> taskList = new ArrayList<>();
        taskList.add(task);
        taskList.add(task2);
        taskList.add(task3);
        List<Task> result = taskController.sortByDate(taskList);
        Assertions.assertEquals(taskList, result);
    }


}

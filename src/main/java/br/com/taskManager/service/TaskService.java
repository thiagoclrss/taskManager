package br.com.taskManager.service;

import br.com.taskManager.entity.Task;
import br.com.taskManager.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {
    @Autowired
    private TaskRepository taskRepository;
    public List<Task> getTaskList(){
        Iterable<Task> taskIterable = this.taskRepository.findAll();
        return Streamable.of(taskIterable).toList();
    }
}

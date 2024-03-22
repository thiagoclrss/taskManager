package br.com.taskManager.service;

import br.com.taskManager.entity.Task;
import br.com.taskManager.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class TaskService {
    @Autowired
    private TaskRepository taskRepository;
    public List<Task> getTaskList(){
        Iterable<Task> taskIterable = this.taskRepository.findAll();
        return Streamable.of(taskIterable).toList();
    }

    public void save(Task task){
        this.taskRepository.save(task);
    }

    public List<Task> findAll() {
        return this.taskRepository.findAll();
    }

    public Task findById(Long id) {
        return this.taskRepository.findById(id).get();
    }

    public void delete(Long id) {
        this.taskRepository.deleteById(id);
    }
}

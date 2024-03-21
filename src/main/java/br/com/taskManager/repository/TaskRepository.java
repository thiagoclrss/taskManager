package br.com.taskManager.repository;

import br.com.taskManager.entity.Task;
import org.springframework.data.repository.CrudRepository;

public interface TaskRepository extends CrudRepository<Task, Long> {

}

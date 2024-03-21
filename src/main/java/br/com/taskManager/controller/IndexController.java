package br.com.taskManager.controller;

import br.com.taskManager.entity.Task;
import br.com.taskManager.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class IndexController {
    @Autowired
    private TaskService taskService;

    @GetMapping("/task/list")
    public ModelAndView getList(){
        ModelAndView mv = new ModelAndView("index");
        return mv;
    }

    @GetMapping("/task/form/add")
    public ModelAndView getFormAdd(){
        ModelAndView mv = new ModelAndView("taskform");
        List<Task> taskList = this.taskService.getTaskList();
        mv.addObject("tasklist", taskList); //esse método atribui a lista de tarefas a palavra tasklist para ser utilizada no html
        return mv;
    }

}

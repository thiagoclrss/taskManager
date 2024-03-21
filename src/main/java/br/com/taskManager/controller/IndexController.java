package br.com.taskManager.controller;

import br.com.taskManager.entity.Task;
import br.com.taskManager.service.TaskService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

    @PostMapping
    public String saveTask(@Valid Task task, BindingResult result, RedirectAttributes redirect){

        if(result.hasErrors()){
            redirect.addFlashAttribute("mensagem", "Verifique os campos obrigatórios");
            return "redirec:/task/form/add";
        }
        return "redirect:/task/list";
    }


}

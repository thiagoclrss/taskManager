package br.com.taskManager.controller;

import br.com.taskManager.entity.Task;
import br.com.taskManager.service.TaskService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Controller
public class TaskController {
    @Autowired
    private TaskService taskService;

    @GetMapping("/task/list")
    public ModelAndView getList() throws ParseException {
        ModelAndView mv = new ModelAndView("index");
        List<Task> taskList = this.taskService.findAll();
        taskList = sortByDate(taskList);
        mv.addObject("tasklist", taskList);
        return mv;
    }

    @GetMapping("/task/form/add")
    public ModelAndView getFormAdd(){
        ModelAndView mv = new ModelAndView("taskform");
        return mv;
    }

    @PostMapping("/task/form/save")
    public String saveTask(@Valid Task task, BindingResult result, RedirectAttributes redirect){

        if(result.hasErrors()){
            redirect.addFlashAttribute("mensagem", "Verifique os campos obrigatórios");
            return "redirect:/task/form/add";
        }
        this.taskService.save(task);
        return "redirect:/task/list";
    }

    @GetMapping("/edit/{id}")
    public ModelAndView getEdit(@PathVariable("id") Long id) {
        ModelAndView mv = new ModelAndView("taskform");
        Task task = this.taskService. findById(id);
        mv.addObject("task", task);
        return mv;
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id){
        this.taskService.delete(id);
        return "redirect:/task/list";
    }

    public List<Task> sortByDate(List<Task> taskList) throws ParseException {
        List<Task> dateTaskList = taskList;
        for(Task task : dateTaskList){
            SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
            Date dataFormatada = formato.parse(task.getExpiration());
            task.setExpirationDate(dataFormatada);
        }
        Collections.sort(dateTaskList);
        return dateTaskList;
    }
}

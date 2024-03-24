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

    //Método para acessar a lista de tarefas
    @GetMapping("/task/list")
    public ModelAndView getList() throws ParseException {
        ModelAndView mv = new ModelAndView("index");
        List<Task> taskList = this.taskService.findAll();
        taskList = sortByDate(taskList);
        mv.addObject("tasklist", taskList);
        return mv;
    }
    //Método para acessar o formulário html para adicionar uma tarefa
    @GetMapping("/task/form/add")
    public ModelAndView getFormAdd(){
        ModelAndView mv = new ModelAndView("taskform");
        return mv;
    }
    //Método post que verifica se o usuário passou os campos Título e/ou validade em branco, caso sim uma mensagem "Verifique os campos obrigatórios" é lançada na tela
    //Caso os campos estejam devidamente preenchidos a tarefa é adicionada no banco. A página é redirecionanda para a lista de tarefas
    @PostMapping("/task/form/save")
    public String saveTask(@Valid Task task, BindingResult result, RedirectAttributes redirect){

        if(result.hasErrors()){
            redirect.addFlashAttribute("mensagem", "Verifique os campos obrigatórios");
            return "redirect:/task/form/add";
        }
        this.taskService.save(task);
        return "redirect:/task/list";
    }
    //Método para acessar e editar uma tarefa com base no ID
    @GetMapping("/edit/{id}")
    public ModelAndView getEdit(@PathVariable("id") Long id) {
        ModelAndView mv = new ModelAndView("taskform");
        Task task = this.taskService. findById(id);
        mv.addObject("task", task);
        return mv;
    }
    //Método para acessar e deletar uma tarefa com base no ID
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id){
        this.taskService.delete(id);
        return "redirect:/task/list";
    }
    //Método para ordenar a lista de tarefas com base na data, a data é convertida de String para Date. A lista é ordenada utilizando Collections.sort()
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

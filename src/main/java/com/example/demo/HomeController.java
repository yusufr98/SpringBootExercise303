package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
public class HomeController {

    @Autowired
    TaskRepository taskRepository;

    @RequestMapping("/")
    public String listTasks(Model model){
        model.addAttribute("tasks", taskRepository.findAll());
        return "list";
    }

    @GetMapping("/add")
    public String taskForm(Model model){
        model.addAttribute("task", new Task());
        return "taskform";
    }

    @PostMapping("/process")
    public String processForm(@Valid Task task, BindingResult result){
        if (result.hasErrors()){
            return "taskform";
        }
        taskRepository.save(task);
        return "redirect:/";
    }

    @RequestMapping("/detail/{id}")
    public String showCourse(@PathVariable("id") long id, Model model){
        model.addAttribute("task", taskRepository.findById(id).get());
        return "show";
    }

    @RequestMapping("/update/{id}")
    public String updateTask(@PathVariable("id") long id, Model model){
        model.addAttribute("task", taskRepository.findById(id).get());
        return "taskform";
    }
    @RequestMapping("/delete/{id}")
    public String delTask(@PathVariable("id") long id){
        taskRepository.deleteById(id);
        return "redirect:/";
    }
}

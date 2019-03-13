package com.greenfox.controller;

import com.greenfox.model.Todo;
import com.greenfox.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/todo")
public class TodoController {

    private TodoService todoService;

    @Autowired
    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @RequestMapping(value = {"", "/list"}, method = RequestMethod.GET)
    public String list(Model model, @RequestParam(value = "isActive", required = false) boolean isActive) {
        model.addAttribute("todos", isActive ? todoService.listActive() : todoService.listAll());
        return "todolist";
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String getAdd() {
        return "add";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String postAdd(String newToDo) {
        todoService.addToDo(newToDo);
        return "redirect:/todo";
    }

    @RequestMapping(value = "/{id}/delete", method = RequestMethod.DELETE)
    public String delete(@PathVariable("id") Long id) {
        todoService.deleteToDo(id);
        return "redirect:/todo";
    }

    @RequestMapping(value = "/{id}/delete")
    public String delete2(@PathVariable("id") Long id) {
        todoService.deleteToDo(id);
        return "redirect:/todo";
    }

    @RequestMapping(value = "/{id}/edit")
    public String getEdit(@PathVariable("id") Long id, Model model) {
        model.addAttribute("todo", todoService.getEditable(id));
        return "edit";
    }

    @RequestMapping(value = "/{id}/edit", method = RequestMethod.PUT)
    public String postEdit(String newToDo, boolean urgent, boolean done, @PathVariable Long id) {
        todoService.update(id, newToDo, urgent, done);
        return "redirect:/todo";
    }

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public String search(@RequestParam("searchBy") int searchBy, @RequestParam("searchFor") String searchFor, Model model) {
        model.addAttribute("todos", todoService.search(searchBy, searchFor));
        return "todolist";
    }
}

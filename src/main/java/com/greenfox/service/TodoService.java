package com.greenfox.service;

import com.greenfox.model.Todo;
import com.greenfox.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TodoService {
    private TodoRepository todoRepository;

    @Autowired
    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    public List<Todo> listAll() {
        List<Todo> result = new ArrayList<>();
        todoRepository.findAll().forEach(result::add);
        return result;
    }

    public List<Todo> listActive() {
        return listAll().stream().filter(todo -> !todo.isDone()).collect(Collectors.toList());
    }

    public void addToDo(String toDo) {
        todoRepository.save(new Todo(toDo));
    }

    public void addToDo(String toDo, boolean urgent, boolean done) {
        todoRepository.save(new Todo(toDo, urgent, done));
    }

    public void deleteToDo(long id) {
        todoRepository.deleteById(id);
    }

    public Todo getEditable(long id) {
        return todoRepository.findById(id).orElseThrow(NullPointerException::new);
    }

    public void update(long id, String title, boolean urgent, boolean done) {
        Todo todo = todoRepository.findById(id).orElseThrow(NullPointerException::new);
        todo.setTitle(title);
        todo.setUrgent(urgent);
        todo.setDone(done);
        todoRepository.save(todo);
    }

    public List<Todo> search(int searchBy, String searchFor) {
        List<Todo> backUp = new ArrayList<>();
        List<Todo> result;
        todoRepository.findAll().forEach(backUp::add);
        result = backUp.stream()
                .filter(todo -> searchBy == 1 ? todo.getTitle().toLowerCase().contains(searchFor.toLowerCase()) : todo.getId() == Integer.parseInt(searchFor))
                .collect(Collectors.toList());

        return result;
    }
}

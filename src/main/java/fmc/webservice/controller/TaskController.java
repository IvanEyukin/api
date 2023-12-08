package fmc.webservice.controller;

import fmc.utils.Utils;
import fmc.webservice.dto.rest.StatusResponce;
import fmc.webservice.dto.rest.TaskListResponce;
import fmc.webservice.dto.rest.TaskRequest;
import fmc.webservice.dto.rest.TaskResponce;
import fmc.webservice.service.TaskService;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import javax.validation.Valid;

@RestController
@RequestMapping("/task")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService task = new TaskService();
    private Utils utils = new Utils();

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public TaskResponce create(@Valid @RequestBody TaskRequest taskRequest, HttpServletRequest request) throws JsonProcessingException {
        return task.create(utils.getMessageId(), taskRequest);
    }

    @GetMapping()
    public TaskListResponce getTask(HttpServletRequest request) throws JsonProcessingException {
        return task.getTaskList(utils.getMessageId());
    }

    @PutMapping(value = "/{id}")
    public StatusResponce upddateTask(@PathVariable(value = "id") int taskId, @Valid @RequestBody TaskRequest taskRequest, HttpServletRequest request) {
        return task.updateTask(utils.getMessageId(), taskId, taskRequest);
    }

    @DeleteMapping(value = "/{id}")
    public StatusResponce deleteTask(@PathVariable(value = "id") int taskId) {
        return task.deleteTask(utils.getMessageId(), taskId);
    }
}
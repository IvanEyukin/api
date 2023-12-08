package fmc.webservice.service;

import fmc.database.client.Client;
import fmc.dto.Error;
import fmc.dto.Task;
import fmc.webservice.dto.rest.Status;
import fmc.webservice.dto.rest.StatusResponce;
import fmc.webservice.dto.rest.TaskListResponce;
import fmc.webservice.dto.rest.TaskRequest;
import fmc.webservice.dto.rest.TaskResponce;
import fmc.webservice.dto.rest.TaskListResponce.TaskListContent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class TaskService {

    @Autowired
    private Client client = new Client();

    public TaskResponce create(String messageId, TaskRequest taskRequest) {
        TaskResponce responce = new TaskResponce();
        TaskResponce.TaskContent content = responce.new TaskContent();
        try {
            int taskId = client.addTask(taskRequest.getCreator(), taskRequest.getTitle(), taskRequest.getContent(), taskRequest.getCreationDate());
            content.setId(taskId);
            responce.setStatus(Status.OK);
        } catch (Exception e) {
            log.error(messageId, e);
            responce.setStatus(Status.ERROR);
            responce.setErrorCode(Error.ErrorCode.Exception.name());
            responce.setErrorMessage(Error.ErrorMessage.Exception.name());
        }
        responce.setRequestGuid(messageId);
        responce.setContent(content);
        return responce;
    }

    public TaskListResponce getTaskList(String messageId) {
        TaskListResponce responce = new TaskListResponce();
        List<TaskListResponce.TaskListContent> contentList = new ArrayList<TaskListResponce.TaskListContent>();
        responce.setRequestGuid(messageId);
        try {
            List<Task> taskList = client.getTaskList();
            for (Task task : taskList) {
               TaskListContent content = responce.new TaskListContent();
               content.setId(task.getId());
               content.setCreator(task.getCreator());
               content.setTitle(task.getTitle());
               content.setContent(task.getContent());
               content.setCreationDate(task.getCreationDate());
               contentList.add(content);
            }
            responce.setStatus(Status.OK);
            responce.setContent(contentList);
        } catch (Exception e) {
            log.error(messageId, e);
            responce.setStatus(Status.ERROR);
            responce.setErrorCode("100");
            responce.setErrorMessage("Внутренняя ошибка");
        }
        return responce;
    }

    public StatusResponce updateTask(String messageId, int taskId, TaskRequest taskRequest) {
        StatusResponce responce = new StatusResponce();
        responce.setRequestGuid(messageId);
        try {
            client.updateTask(taskId, taskRequest.getTitle(), taskRequest.getContent());
            responce.setStatus(Status.OK);
        } catch (Exception e) {
            log.error(messageId, e);
            responce.setStatus(Status.ERROR);
            responce.setErrorCode("100");
            responce.setErrorMessage("Внутренняя ошибка");
        }
        return responce;
    }

    public StatusResponce deleteTask(String messageId, int taskId) {
        StatusResponce responce = new StatusResponce();
        responce.setRequestGuid(messageId);
        try {
            client.deleteTask(taskId);
            responce.setStatus(Status.OK);
        } catch (Exception e) {
            log.error(messageId, e);
            responce.setStatus(Status.ERROR);
            responce.setErrorCode("100");
            responce.setErrorMessage("Внутренняя ошибка");
        }
        return responce;
    }
}
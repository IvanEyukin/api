package fmc.webservice.service;

import fmc.database.client.Client;
import fmc.dto.Task;
import fmc.entity.Error.ErrorCode;
import fmc.entity.LogMessage;
import fmc.mapper.Mapper;
import fmc.webservice.dto.rest.StatusResponce;
import fmc.webservice.dto.rest.TaskListResponce;
import fmc.webservice.dto.rest.TaskRequest;
import fmc.webservice.dto.rest.TaskResponce;

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
    private Mapper mapper = new Mapper();

    public TaskResponce create(String messageId, TaskRequest taskRequest) {
        TaskResponce responce = new TaskResponce();
        TaskResponce.TaskContent content = responce.new TaskContent();
        log.info(LogMessage.IN, messageId, taskRequest);
        try {
            int taskId = client.addTask(mapper.mappingTask(taskRequest));
            content.setId(taskId);
            responce = mapper.mappingSuccessTaskResponce(messageId);
            responce.setContent(content);
        } catch (Exception e) {
            log.error(messageId, e);
            responce = mapper.mappingErrorTaskResponce(messageId, ErrorCode.Exception);
        }
        log.info(LogMessage.OUT, messageId, responce);
        return responce;
    }

    public TaskListResponce getTaskList(String messageId) {
        TaskListResponce responce = new TaskListResponce();
        List<TaskListResponce.TaskListContent> contentList = new ArrayList<TaskListResponce.TaskListContent>();
        log.info(LogMessage.IN, messageId);
        try {
            List<Task> taskList = client.getTaskList();
            for (Task task : taskList) {
               contentList.add(mapper.mappingTaskListResponceContent(task));
            }
            responce = mapper.mappingSuccessTaskListResponce(messageId);
            responce.setContent(contentList);
        } catch (Exception e) {
            log.error(messageId, e);
            responce = mapper.mappingErrorTaskListResponce(messageId, ErrorCode.Exception);
        }
        log.info(LogMessage.OUT, messageId, responce);
        return responce;
    }

    public StatusResponce updateTask(String messageId, int taskId, TaskRequest taskRequest) {
        StatusResponce responce = new StatusResponce();
        log.info(LogMessage.IN, messageId, taskRequest);
        try {
            client.updateTask(mapper.mappingTask(taskRequest));
            responce = mapper.mappingSuccessResponce(messageId);
        } catch (Exception e) {
            log.error(messageId, e);
            responce = mapper.mappingErrorResponce(messageId, ErrorCode.Exception);
        }
        log.info(LogMessage.OUT, messageId, responce);
        return responce;
    }

    public StatusResponce deleteTask(String messageId, int taskId) {
        StatusResponce responce = new StatusResponce();
        log.info(LogMessage.IN, messageId, taskId);
        try {
            client.deleteTask(taskId);
            responce = mapper.mappingSuccessResponce(messageId);
        } catch (Exception e) {
            log.error(messageId, e);
            responce = mapper.mappingErrorResponce(messageId, ErrorCode.Exception);
        }
        log.info(LogMessage.OUT, messageId, responce);
        return responce;
    }
}
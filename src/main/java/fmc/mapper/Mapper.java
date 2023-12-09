package fmc.mapper;

import fmc.dto.Task;
import fmc.dto.User;
import fmc.entity.Error;
import fmc.entity.Error.ErrorCode;
import fmc.webservice.dto.rest.Status;
import fmc.webservice.dto.rest.StatusResponce;
import fmc.webservice.dto.rest.TaskListResponce;
import fmc.webservice.dto.rest.TaskRequest;
import fmc.webservice.dto.rest.TaskResponce;
import fmc.webservice.dto.rest.UserRequest;
import fmc.webservice.dto.rest.UserResponce;

public class Mapper {

    public User mappingUser(UserRequest userRequest) {
        User user = new User();
        user.setName(userRequest.getName());
        user.setLogin(userRequest.getLogin());
        user.setPassword(userRequest.getPassword());
        user.setRole(userRequest.getRole());
        user.setCreationDate(userRequest.getCreationDate());
        return user;
    }

    public UserResponce.UserContent mappingUserResponceContent(User user, boolean password) {
        UserResponce userResponce = new UserResponce();
        UserResponce.UserContent content = userResponce.new UserContent();
        content.setName(user.getName());
        content.setLogin(user.getLogin());
        content.setRole(user.getRole());
        content.setPassword(password);
        return content;
    }

    public Task mappingTask(TaskRequest taskRequest) {
        Task task = new Task();
        task.setCreator(taskRequest.getCreator());
        task.setTitle(taskRequest.getTitle());
        task.setContent(taskRequest.getContent());
        task.setStatus(taskRequest.getStatus() == null ? "open" : taskRequest.getStatus());
        task.setCreationDate(taskRequest.getCreationDate());
        return task;
    }

    public TaskResponce.TaskContent mappingTaskResponceContent(Task task) {
        TaskResponce taskResponce = new TaskResponce();
        TaskResponce.TaskContent content = taskResponce.new TaskContent();
        content.setId(task.getId());
        return content;
    }

    public TaskListResponce.TaskListContent mappingTaskListResponceContent(Task task) {
        TaskListResponce taskResponce = new TaskListResponce();
        TaskListResponce.TaskListContent content = taskResponce.new TaskListContent();
        content.setId(task.getId());
        content.setCreator(task.getCreator());
        content.setTitle(task.getTitle());
        content.setContent(task.getContent());
        content.setStatus(task.getStatus());
        content.setCreationDate(task.getCreationDate());
        return content;
    }

    public StatusResponce mappingSuccessResponce(String messageId) {
        StatusResponce responce = new StatusResponce();
        responce.setStatus(Status.OK);
        responce.setRequestGuid(messageId);
        return responce;
    }

    public StatusResponce mappingErrorResponce(String messageId, ErrorCode code) {
        StatusResponce responce = new StatusResponce();
        responce.setStatus(Status.ERROR);
        responce.setRequestGuid(messageId);
        responce.setErrorCode(code.toString());
        responce.setErrorMessage(Error.CONSTANTS.get(code).toString());
        return responce;
    }

    public StatusResponce mappingErrorResponce(String messageId, ErrorCode code, String message) {
        StatusResponce responce = new StatusResponce();
        responce.setStatus(Status.ERROR);
        responce.setRequestGuid(messageId);
        responce.setErrorCode(code.toString());
        responce.setErrorMessage(Error.CONSTANTS.get(code).toString().concat(message));
        return responce;
    }

    public UserResponce mappingSuccessUserResponce(String messageId) {
        UserResponce responce = new UserResponce();
        responce.setStatus(Status.OK);
        responce.setRequestGuid(messageId);
        return responce;
    }

    public UserResponce mappingErrorUserResponce(String messageId, ErrorCode code) {
        UserResponce responce = new UserResponce();
        responce.setStatus(Status.ERROR);
        responce.setRequestGuid(messageId);
        responce.setErrorCode(code.toString());
        responce.setErrorMessage(Error.CONSTANTS.get(code).toString());
        return responce;
    }

    public UserResponce mappingErrorUserResponce(String messageId, ErrorCode code, String message) {
        UserResponce responce = new UserResponce();
        responce.setStatus(Status.ERROR);
        responce.setRequestGuid(messageId);
        responce.setErrorCode(code.toString());
        responce.setErrorMessage(Error.CONSTANTS.get(code).toString().concat(message));
        return responce;
    }

    public TaskResponce mappingSuccessTaskResponce(String messageId) {
        TaskResponce responce = new TaskResponce();
        responce.setStatus(Status.OK);
        responce.setRequestGuid(messageId);
        return responce;
    }

    public TaskResponce mappingErrorTaskResponce(String messageId, ErrorCode code) {
        TaskResponce responce = new TaskResponce();
        responce.setStatus(Status.ERROR);
        responce.setRequestGuid(messageId);
        responce.setErrorCode(code.toString());
        responce.setErrorMessage(Error.CONSTANTS.get(code).toString());
        return responce;
    }

    public TaskResponce mappingErrorTaskResponce(String messageId, ErrorCode code, String message) {
        TaskResponce responce = new TaskResponce();
        responce.setStatus(Status.ERROR);
        responce.setRequestGuid(messageId);
        responce.setErrorCode(code.toString());
        responce.setErrorMessage(Error.CONSTANTS.get(code).toString().concat(message));
        return responce;
    }

    public TaskListResponce mappingSuccessTaskListResponce(String messageId) {
        TaskListResponce responce = new TaskListResponce();
        responce.setStatus(Status.OK);
        responce.setRequestGuid(messageId);
        return responce;
    }

    public TaskListResponce mappingErrorTaskListResponce(String messageId, ErrorCode code) {
        TaskListResponce responce = new TaskListResponce();
        responce.setStatus(Status.ERROR);
        responce.setRequestGuid(messageId);
        responce.setErrorCode(code.toString());
        responce.setErrorMessage(Error.CONSTANTS.get(code).toString());
        return responce;
    }

    public TaskListResponce mappingErrorTaskListResponce(String messageId, ErrorCode code, String message) {
        TaskListResponce responce = new TaskListResponce();
        responce.setStatus(Status.ERROR);
        responce.setRequestGuid(messageId);
        responce.setErrorCode(code.toString());
        responce.setErrorMessage(Error.CONSTANTS.get(code).toString().concat(message));
        return responce;
    }
}
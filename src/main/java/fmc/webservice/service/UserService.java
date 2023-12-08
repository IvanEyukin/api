package fmc.webservice.service;

import fmc.database.client.Client;
import fmc.webservice.dto.rest.Status;
import fmc.webservice.dto.rest.StatusResponce;
import fmc.webservice.dto.rest.UserRequest;
import fmc.webservice.dto.rest.UserResponce;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import java.time.LocalDateTime;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    @Autowired
    private Client client = new Client();
    
    public UserResponce cheacPassword(String messageId, UserRequest user) {
        UserResponce responce = new UserResponce();
        UserResponce.UserContent content = responce.new UserContent();
        try {
            if (user.getPassword().equals(client.checkPassword(user.getLogin()))) {
                content.setPassword(true);
            } else {
                content.setPassword(false);
            }
            responce.setStatus(Status.OK);
        } catch (Exception e) {
            log.error(messageId, e);
            responce.setStatus(Status.ERROR);
            responce.setErrorCode("100");
            responce.setErrorMessage("Внутренняя ошибка");
        }
        responce.setRequestGuid(messageId);
        content.setLogin(user.getLogin());
        responce.setContent(content);
        return responce;
    }

    public StatusResponce addUser(String messageId, UserRequest user) {
        StatusResponce responce = new StatusResponce();
        responce.setRequestGuid(messageId);
        try {
            boolean cheackLogin = client.checkUser(user.getLogin());
            if (cheackLogin) {
                responce.setStatus(Status.ERROR);
                responce.setErrorCode("020");
                responce.setErrorMessage("Пользователь с таким логином уже существует");
            } else {
                client.addUser(user.getLogin(), user.getPassword(), LocalDateTime.now());
                responce.setStatus(Status.OK);
            }
        } catch (Exception e) {
            log.error(messageId, e);
            responce.setStatus(Status.ERROR);
            responce.setErrorCode("100");
            responce.setErrorMessage("Внутренняя ошибка");
        }
        return responce;
    }

    public StatusResponce updateUser(String messageId, String login, UserRequest user) {
        StatusResponce responce = new StatusResponce();
        responce.setRequestGuid(messageId);
        try {
            client.updateUser(login, user.getPassword(), null);
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
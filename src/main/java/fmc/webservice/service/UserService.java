package fmc.webservice.service;

import fmc.database.client.Client;
import fmc.entity.LogMessage;
import fmc.entity.Error.ErrorCode;
import fmc.mapper.Mapper;
import fmc.webservice.dto.rest.StatusResponce;
import fmc.webservice.dto.rest.UserRequest;
import fmc.webservice.dto.rest.UserResponce;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    @Autowired
    private Client client = new Client();
    private Mapper mapper = new Mapper();
    
    public UserResponce cheacPassword(String messageId, UserRequest user) {
        UserResponce responce = new UserResponce();
        UserResponce.UserContent content = responce.new UserContent();
        log.info(LogMessage.IN, messageId, user);
        try {
            if (user.getPassword().equals(client.checkPassword(user.getLogin()))) {
                content.setPassword(true);
            } else {
                content.setPassword(false);
            }
            responce = mapper.mappingSuccessUserResponce(messageId);
        } catch (Exception e) {
            log.error(messageId, e);
            responce = mapper.mappingErrorUserResponce(messageId, ErrorCode.Exception);
        }
        content.setLogin(user.getLogin());
        responce.setContent(content);
        log.info(LogMessage.OUT, messageId, responce);
        return responce;
    }

    public StatusResponce addUser(String messageId, UserRequest user) {
        StatusResponce responce = new StatusResponce();
        log.info(LogMessage.IN, messageId, user);
        try {
            boolean cheackLogin = client.checkUser(user.getLogin());
            if (cheackLogin) {
                responce = mapper.mappingErrorResponce(messageId, ErrorCode.Logic, "Пользователь с таким логином уже существует");
            } else {
                client.addUser(mapper.mappingUser(user));
                responce = mapper.mappingSuccessResponce(messageId);
            }
        } catch (Exception e) {
            log.error(messageId, e);
            responce = mapper.mappingErrorResponce(messageId, ErrorCode.Exception);
        }
        log.info(LogMessage.OUT, messageId, responce);
        return responce;
    }

    public StatusResponce updateUser(String messageId, UserRequest user) {
        StatusResponce responce = new StatusResponce();
        log.info(LogMessage.IN, messageId, user);
        try {
            client.updateUser(mapper.mappingUser(user));
            responce = mapper.mappingSuccessResponce(messageId);
        } catch (Exception e) {
            log.error(messageId, e);
            responce = mapper.mappingErrorResponce(messageId, ErrorCode.Exception);
        }
        log.info(LogMessage.OUT, messageId, responce);
        return responce;
    }
}
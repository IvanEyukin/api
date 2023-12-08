package fmc.webservice.controller;

import fmc.utils.Utils;
import fmc.webservice.dto.rest.StatusResponce;
import fmc.webservice.dto.rest.UserRequest;
import fmc.webservice.dto.rest.UserResponce;
import fmc.webservice.service.UserService;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.MediaType;

import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import javax.validation.Valid;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    
    private final UserService user = new UserService();
    private Utils utils = new Utils();

    @PostMapping(value = "/checkPassword", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public UserResponce check(@Valid @RequestBody UserRequest userRequest, HttpServletRequest request) throws JsonProcessingException {
        return user.cheacPassword(utils.getMessageId(), userRequest);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public StatusResponce addUser(@Valid @RequestBody UserRequest UserRequest, HttpServletRequest request) throws JsonProcessingException {
        return user.addUser(utils.getMessageId(), UserRequest);
    }

    @PutMapping(value = "/{login}")
    public StatusResponce updateUser(@PathVariable(value = "login") String login, @Valid @RequestBody UserRequest UserRequest, HttpServletRequest request) throws JsonProcessingException {
        return user.updateUser(utils.getMessageId(), login, UserRequest);
    }
}
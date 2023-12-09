package fmc.webservice.controller;

import fmc.entity.Error;
import fmc.entity.LogMessage;
import fmc.mapper.Mapper;
import fmc.utils.Utils;
import fmc.webservice.dto.rest.StatusResponce;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Slf4j
@ControllerAdvice
public class ControllerExceptionAdvice {

    private Utils utils = new Utils();
    private Mapper mapper = new Mapper();

    @ResponseBody
    @ExceptionHandler({MethodArgumentNotValidException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public StatusResponce onConstraintValidationException (MethodArgumentNotValidException e) {
        String messageId = utils.getMessageId();
        StatusResponce responce = mapper.mappingErrorResponce(messageId, Error.ErrorCode.Request, e.getMessage());
        log.info(LogMessage.OUT, messageId, responce);
        return responce;
    }

    @ResponseBody
    @ExceptionHandler({Exception.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public StatusResponce onException (Exception e) {
        String messageId = utils.getMessageId();
        StatusResponce responce = mapper.mappingErrorResponce(messageId, Error.ErrorCode.Exception);
        log.error(messageId, e);
        log.info(LogMessage.OUT, messageId, responce);
        return responce;
    }
}
package fmc.webservice.controller;

import fmc.utils.Utils;
import fmc.webservice.dto.rest.Status;
import fmc.webservice.dto.rest.StatusResponce;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.ConstraintViolationException;

@ControllerAdvice
public class ErrorHandlingControllerAdvice {

    private Utils utils = new Utils();

    @ResponseBody
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public StatusResponce onConstraintValidationException (ConstraintViolationException e) {
        StatusResponce responce = new StatusResponce();
        responce.setRequestGuid(utils.getMessageId());
        responce.setStatus(Status.ERROR);
        responce.setErrorCode("040");
        responce.setErrorMessage(String.format("Некорректный запрос: ", e.getMessage()));
        return responce;
    }
}

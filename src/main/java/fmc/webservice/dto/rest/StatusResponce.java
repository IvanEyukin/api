package fmc.webservice.dto.rest;

import jakarta.validation.constraints.NotBlank;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class StatusResponce {
    /* 
     * Статус операции
    */
    @NotBlank
    Status status;

    /* 
     * guid сообщения
    */
    @NotBlank
    String requestGuid;

    /* 
     * Код ошибки
    */
    String errorCode;

    /* 
     * Сообщение об ошибки
    */
    String errorMessage;
}
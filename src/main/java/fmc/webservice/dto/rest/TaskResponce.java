package fmc.webservice.dto.rest;

import javax.validation.constraints.NotBlank;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TaskResponce {
    /*
     * Статус операции
     */
    @NotBlank
    Status status;

    /*
     * Guid сообщения
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

    /*
     * Контент
     */
    TaskContent content;

    @Data
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public class TaskContent {
        /*
         * Id задачи
         */
        int id;
    }
}
package fmc.webservice.dto.rest;

import jakarta.validation.constraints.NotBlank;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TaskListResponce {
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
    List<TaskListContent> content;

    @Data
    public class TaskListContent {
        /*
         * ID задачи
         */
        int id;

        /*
         * Автор
         */
        String creator;

        /*
         * Название задачи
         */
        String title;

        /*
         * Контент
         */
        String content;

        /*
         * Статус
         */
        String status;

        /*
         * Дата создания
         */
        LocalDateTime creationDate;
    }
}
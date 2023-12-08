package fmc.webservice.dto.rest;

import javax.validation.constraints.NotBlank;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserResponce {
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
    UserContent content;

    @Data
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public class UserContent {
        /*
         * Имя пользователя
         */
        String name;

        /*
         * Логин пользователя
         */
        String login;

        /*
         * Пароль пользователя
         */
        Boolean password;
    }
}
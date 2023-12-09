package fmc.webservice.dto.rest;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import java.time.LocalDateTime;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserRequest {
    /* 
     * Имя пользователя
    */
    String name;

    /* 
     * Логин пользователя
    */
    @NotBlank
    String login;

    /* 
     * Пароль пользователя
    */
    @NotBlank
    String password;

    /* 
     * Роль пользователя
    */
    String role;

    /* 
     * Дата регистрации
    */
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    LocalDateTime creationDate;
}
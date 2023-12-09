package fmc.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class User {
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
    String password;

    /* 
     * Пароль пользователя
    */
    String role;

    /* 
     * Дата регистрации
    */
    LocalDateTime creationDate;
}
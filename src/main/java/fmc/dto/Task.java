package fmc.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class Task {
    /* 
     * Id задачи
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
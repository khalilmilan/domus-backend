package com.message.mmessage.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Message extends BaseEntity {

    @Id
    @GeneratedValue
    private Long idMessage;
    private String content;
    private Integer status;
    private Long idSender;
    private Long idReciver;
    private Long idDiscussion;
    private Date date;

}

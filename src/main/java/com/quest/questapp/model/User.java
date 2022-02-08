package com.quest.questapp.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@Table(name = "user")
public class User {

    @Id
    Long id;

    String userName;
    String password;

}

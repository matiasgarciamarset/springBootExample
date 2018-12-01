package com.simple.base.project.repository.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity @Table( name = "Authentication" )
public class Authentication {
    @Id
    private String user;
    @Column
    private String password;
    @Column
    private String token;
}

package com.cosight.ssk.entity;

import static lombok.AccessLevel.PROTECTED;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "accounts")
@Entity
@NoArgsConstructor(access = PROTECTED)
@Getter
public class Account {

    @Id
    @Column
    private Long id;

    @Column
    private String username;

    @Column
    private String password;

}

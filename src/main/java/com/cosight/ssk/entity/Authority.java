package com.cosight.ssk.entity;

import static javax.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

import com.cosight.ssk.constant.RoleType;
import com.cosight.ssk.constant.RoleTypeConverter;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "authorities")
@Entity
@NoArgsConstructor(access = PROTECTED)
@Getter
public class Authority {

    @NotNull
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "authority_no")
    private Integer id;

    @NotNull
    @Convert(converter = RoleTypeConverter.class)
    @Column
    private RoleType role;

}

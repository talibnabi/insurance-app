package com.company.insuranceapp.model.entity;


import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import static com.company.insuranceapp.model.entity.User.TABLE_NAME;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = TABLE_NAME)
public class User {
    public static final String TABLE_NAME = "user";
    @Id
    public long id;
}

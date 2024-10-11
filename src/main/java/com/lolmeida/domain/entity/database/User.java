package com.lolmeida.domain.entity.database;


import com.lolmeida.domain.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.extern.slf4j.Slf4j;


@Getter
@Setter
@Slf4j
@Builder
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "tUtilizadores") //, schema = "sql7509759")
public class User extends BaseEntity {
    @Id
    private String id;
    private String mail;
    private String name;
    private String phoneNumber;
    private String address;
}

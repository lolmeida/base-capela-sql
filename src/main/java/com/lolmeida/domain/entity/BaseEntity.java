package com.lolmeida.domain.entity;

import com.lolmeida.Utils;



import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Version;

import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
public abstract class BaseEntity {

/*
    @Column(name = "ModificadoPor")
    protected String updatedBy;

    @Column(name = "Activo")
    protected boolean active;

    @Column(name = "Nota")
    protected String note;

    @Column(name = "Anexo")
    protected String attachment;

    @Column(name = "Utilizador",nullable = false)
    protected String createdBy;

    @Column(name = "Foto")
    protected String image;

    @Column(name = "Descricao")
    protected String description;

    @Column(name = "Observacoes")
    protected String comments;

    @Column(name = "Contador", columnDefinition = "INT DEFAULT 0")
    protected int Counter;
    @Column(name = "TempoCriacao",nullable = false)
    protected Long createdTime;

    @Column(name = "TempoModificacao")
    protected Long updatedTime;

    @Version
    @Column(name = "Versao", columnDefinition = "INT DEFAULT 0",nullable = false)
    protected int version;
    */

    //@Column(name = "Data", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP",nullable = false)
    //protected LocalDateTime date;

    @Column(name = "DataCriacao", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP",nullable = false)
    protected LocalDateTime createdAt;

    //@Column(name = "DataModificacao", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    //protected LocalDateTime updatedAt;


    @PrePersist
    protected void onCreate() {
        //this.createdTime = Utils.currentTime;
        this.createdAt = Utils.currentDateTime;
        //this.createdBy = Utils.activeUser();

        //this.date = Utils.currentDateTime;
        //this.active = true;
    }

    @PreUpdate
    protected void onUpdate() {
        //this.updatedAt = Utils.currentDateTime;
        //this.updatedTime = Utils.currentTime;
        //this.updatedBy = Utils.activeUser();
    }
}

package com.lolmeida.dto.response;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record UtilizadorResponse(
        String Mail,
        String Nome,
        String Telefone,
        String Morada,
        String Assinatura,
        String Previlegio,
        String Perfil,
        String Clientes,
        String Recebimentos,
        String Cargas,
        String DiasEdicaoDocumento,
        String PrazoAnularEstadoDias,
        String DiasEliminarDocumento,
        String QtdMaxFactDivida,
        String DiasMaxFactDivida,
        String HojeMenosData,

        // BaseEntity
        String id,
        boolean active,
        String note,
        String description,
        String attachment,
        String image,
        String createdBy,
        LocalDateTime createdAt,
        Long createdTime,
        String updatedBy,
        LocalDateTime updatedAt,
        Long updatedTime,
        LocalDateTime date
) {
}

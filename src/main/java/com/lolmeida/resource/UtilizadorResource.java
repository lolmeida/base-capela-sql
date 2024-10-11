package com.lolmeida.resource;

import com.lolmeida.Utils;
import com.lolmeida.dto.request.UtilizadorRequest;
import com.lolmeida.dto.response.UtilizadorResponse;
import com.lolmeida.entity.database.Utilizador;
import com.lolmeida.service.UtilizadorService;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;


import java.util.List;

@Path("/user")
@RequestScoped
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UtilizadorResource {
    @Inject
    UtilizadorService service;

    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll() {
        List<UtilizadorResponse> data = service.findAll()
                .stream()
                .map(this::objToResponse)
                .toList();
        return Response.ok(data).build();
    }

    @GET
    @Path("/search/{field}/{value}")
    public Response search(
            @PathParam("field") final String field,
            @PathParam("value") final String value) {
        List<UtilizadorResponse> data = service.search( field, value)
                .stream()
                .map(this::objToResponse)
                .toList();
        return Response.ok(data).build();
    }

    @GET
    @Path("/{id}")
    public Response findByCustomer(@PathParam("id") final String id){
        List<UtilizadorResponse> data = service.findBy(id)
                .stream()
                .map(this::objToResponse)
                .toList();
        return Response.ok(data).build();
    }

    @POST
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response save(@RequestBody UtilizadorRequest request) {
        service.save(requestToObj(request));
        //return Response.ok(request).build();

        return Response
                .ok(service.search("Mail", service.save(requestToObj(request))))
                .build();
    }

    private Utilizador requestToObj (UtilizadorRequest request){
        return Utilizador.builder()
                .id(Utils.generateRandomString())
                .Mail(request.Mail())
                .Nome(request.Nome())
                .Telefone(request.Telefone())
                .Morada(request.Morada())
                .build();
    }

    private UtilizadorResponse objToResponse (Utilizador entity) {
        return UtilizadorResponse.builder()
                .Mail(entity.getMail())
                .Nome(entity.getNome())
                .Telefone(entity.getTelefone())
                .Morada(entity.getMorada())
                .Assinatura(entity.getAssinatura())
                .Previlegio(entity.getPrevilegio())
                .Perfil(entity.getPerfil())
                .Clientes(entity.getClientes())
                .Recebimentos(entity.getRecebimentos())
                .Cargas(entity.getCargas())
                .DiasEdicaoDocumento(entity.getDiasEdicaoDocumento())
                .PrazoAnularEstadoDias(entity.getPrazoAnularEstadoDias())
                .DiasEliminarDocumento(entity.getDiasEliminarDocumento())
                .QtdMaxFactDivida(entity.getQtdMaxFactDivida())
                .DiasMaxFactDivida(entity.getDiasMaxFactDivida())
                .HojeMenosData(entity.getHojeMenosData())
                .id(entity.getId())
                .build();
    }
}

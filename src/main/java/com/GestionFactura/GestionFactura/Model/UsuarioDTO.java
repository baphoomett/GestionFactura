package com.GestionFactura.GestionFactura.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
public class UsuarioDTO {

    private int idUsuario;
    private String nombre;
    private String apellido;
    private String email;
    
}


/*
 *  ESTA CLASE ES UN DTO (DATA TRANSFER OBJECT) QUE SE UTILIZA PARA TRANSFERIR 
 *  DATOS ENTRE LA APLICACION Y EL CLIENTE.
 * 
 *  ESTA CLASE TIENE QUE SER UNA CLASE ESPEJO DE LA CLASE USUARIO, PERO SIN 
 *  LA ANOTACION @ENTITY, YA QUE NO SE VA A UTILIZAR PARA GUARDAR EN LA BASE
 * 
 */
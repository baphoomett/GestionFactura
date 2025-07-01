package com.GestionFactura.GestionFactura.Model;


import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "factura")

public class Factura {
 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(length = 200, nullable = false)
    private String nombre;

    @Column(length = 200, nullable = false)
    private String descripcion;

    private Double porcentaje;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate fecha_inicio;

    @DateTimeFormat(pattern = "yyy-MM-dd")
    private LocalDate fecha_fin;

    @Column(nullable = false)
    private Long idCliente;
    
    @Column(nullable = false)
    private String nombreCliente;

    @Column(nullable = false, length = 250)
    private String correoCliente;

    @Column(nullable = false)
    private String estado;
}

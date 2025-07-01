package com.GestionFactura.GestionFactura.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.client.RestTemplate;

import com.GestionFactura.GestionFactura.Model.Factura;
import com.GestionFactura.GestionFactura.Repository.FacturaRepository;
import com.GestionFactura.GestionFactura.Service.FacturaService;

import java.util.Optional;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.assertThat;

class FacturaServiceTest {

    @Mock
    private FacturaRepository facturaRepository;

    @Mock
    private RestTemplate restTemplate;


    @InjectMocks
    private FacturaService facturaService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }



    @Test
void crearFactura() {
    Factura nuevaFactura = new Factura(1,
    "Factura Plan Premium",
    "Plan mensual para cliente premium",
    19.0,
    LocalDate.parse("2025-06-01"),
    LocalDate.parse("2026-06-30"),
    1001L,   "Juan Pérez",
    "juan.perez@cliente.com",
    "ACTIVA");


    when(facturaRepository.save(any(Factura.class))).thenReturn(nuevaFactura);

    Factura resultado = facturaService.crearFactura(nuevaFactura);

    assertThat(resultado).isNotNull();
    assertThat(resultado.getId()).isEqualTo(1);
    assertThat(resultado.getNombre()).isEqualTo("Factura Plan Premium");
    assertThat(resultado.getDescripcion()).isEqualTo("Plan mensual para cliente premium");
    assertThat(resultado.getPorcentaje()).isEqualTo(19.0);
    assertThat(resultado.getFecha_inicio()).isEqualTo(LocalDate.of(2025, 6, 1));
    assertThat(resultado.getFecha_fin()).isEqualTo(LocalDate.of(2026, 6, 30));
    assertThat(resultado.getIdCliente()).isEqualTo(1001);
    assertThat(resultado.getNombreCliente()).isEqualTo("Juan Pérez");
    assertThat(resultado.getCorreoCliente()).isEqualTo("juan.perez@cliente.com");

    verify(facturaRepository).save(nuevaFactura);
}

@Test
void editFactura() {
    Factura existente = new Factura();
    existente.setId(1);
    existente.setNombre("Manolito Toloza");

    Factura actualizado = new Factura();
    actualizado.setNombre("Manolito Toloza");

    when(facturaRepository.findById(1)).thenReturn(Optional.of(existente));
    when(facturaRepository.save(any(Factura.class))).thenAnswer(invocation -> invocation.getArgument(0));

    Factura resultado = facturaService.editFactura(1, actualizado);

    assertThat(resultado.getNombre()).isEqualTo("Manolito Toloza");
    verify(facturaRepository).findById(1);
    verify(facturaRepository).save(any(Factura.class));
}

@Test
void findFacturaById() {
    Factura factura = new Factura();
    factura.setId(1);

    when(facturaRepository.findById(1)).thenReturn(Optional.of(factura));

    Factura resultado = facturaService.findFacturaById(1);

    assertThat(resultado).isNotNull();
    assertThat(resultado.getId()).isEqualTo(1);
    verify(facturaRepository).findById(1);
}


@Test
void anularFactura() {
    Factura factura = new Factura();
    factura.setId(1);
    factura.setEstado("ACTIVA");

    when(facturaRepository.findById(1)).thenReturn(Optional.of(factura));
    when(facturaRepository.save(any(Factura.class))).thenReturn(factura);

    Factura resultado = facturaService.anularFactura(1); 

    assertThat(resultado.getEstado()).isEqualTo("ANULADA");
    verify(facturaRepository).findById(1);
    verify(facturaRepository).save(factura);
}


@Test
void findAllFacturas() {
    List<Factura> lista = List.of(
        new Factura(1,"Factura Plan Premium",
    "Plan mensual para cliente premium",
    19.0,
    LocalDate.parse("2025-06-01"),
    LocalDate.parse("2026-06-30"),
    1001L,   "Juan Pérez",
    "juan.perez@cliente.com",
    "ACTIVA"),

        new Factura(2, 
    "Factura por Canales de TV", 
    "Plan Canales de futbol", 
    19.0, LocalDate.parse("2025-07-01"),
    LocalDate.parse("2025-07-31"), 1002L, 
    "Cliente Dos", "manolito_bellako@gmail.com", 
    "ACTIVA")
    );

    when(facturaRepository.findAll()).thenReturn(lista);

    List<Factura> resultado = facturaService.findAllFacturas();

    assertThat(resultado).hasSize(2);
    assertThat(resultado.get(0).getNombre()).isEqualTo("Factura Plan Premium");
    assertThat(resultado.get(1).getNombre()).isEqualTo("Factura por Canales de TV");

    verify(facturaRepository).findAll();
}


}




    




   





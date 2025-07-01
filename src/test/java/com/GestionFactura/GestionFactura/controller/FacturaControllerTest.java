
package com.GestionFactura.GestionFactura.controller;

import com.GestionFactura.GestionFactura.Controller.FacturaController;
import com.GestionFactura.GestionFactura.Model.Factura;
import com.GestionFactura.GestionFactura.Service.FacturaService;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;

import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@WebMvcTest(FacturaController.class)
class FacturaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FacturaService facturaService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void getFactura() throws Exception {
        Factura f = new Factura(1, "Factura Plan Premium",
    "Plan mensual para cliente premium",
    19.0,
    LocalDate.parse("2025-06-01"),
    LocalDate.parse("2026-06-30"),
    1001L,   "Juan Pérez",
    "juan.perez@cliente.com",
    "ACTIVA");

        when(facturaService.findAllFacturas()).thenReturn(List.of(f));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/factura"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.size()").value(1))
            .andExpect(jsonPath("$[0].nombre").value("Factura Plan Premium"));
    }

    @Test
    void postFactura() throws Exception {
        Factura factura = new Factura(2, "Factura por Canales de TV", 
    "Plan Canales de futbol", 
    19.0, LocalDate.parse("2025-07-01"),
    LocalDate.parse("2025-07-31"), 1002L, 
    "Cliente Dos", "manolito_bellako@gmail.com", 
    "ACTIVA");

        when(facturaService.crearFactura(Mockito.any(Factura.class))).thenReturn(factura);

        mockMvc.perform(MockMvcRequestBuilders
                .post("/api/factura")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(factura)))
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.id").value(2))
            .andExpect(jsonPath("$.nombre").value("Factura por Canales de TV"));
    }
}


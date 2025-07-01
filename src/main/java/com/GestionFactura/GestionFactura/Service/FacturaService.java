package com.GestionFactura.GestionFactura.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.GestionFactura.GestionFactura.Model.Factura;
import com.GestionFactura.GestionFactura.Model.UsuarioDTO;
import com.GestionFactura.GestionFactura.Repository.FacturaRepository;

@Service
public class FacturaService {
    @Autowired
    private FacturaRepository facturaRepository;  
    
    
    @Autowired
    private RestTemplate restTemplate;

    public Factura crearFactura(Factura factura){
        // Llamada al microservicio cliente
        String url = "http://localhost:8081/api/usuario/" + factura.getIdCliente();
        UsuarioDTO cliente = restTemplate.getForObject(url, UsuarioDTO.class);
        if (cliente != null) {
            factura.setNombreCliente(cliente.getNombre() + " " + cliente.getApellido());
            factura.setCorreoCliente(cliente.getEmail());
        }
        
        return facturaRepository.save(factura);
    }


     public List<Factura> findAllFacturas(){
        return facturaRepository.findAll();
    }

    
   

    public Factura editFactura(int id, Factura nuevaFactura) {
    return facturaRepository.findById(id).map(facturaExistente -> {
        facturaExistente.setNombre(nuevaFactura.getNombre());
        facturaExistente.setDescripcion(nuevaFactura.getDescripcion());
        facturaExistente.setPorcentaje(nuevaFactura.getPorcentaje());
        facturaExistente.setFecha_inicio(nuevaFactura.getFecha_inicio());
        facturaExistente.setFecha_fin(nuevaFactura.getFecha_fin());
        facturaExistente.setIdCliente(nuevaFactura.getIdCliente());
        facturaExistente.setNombreCliente(nuevaFactura.getNombreCliente());
        facturaExistente.setCorreoCliente(nuevaFactura.getCorreoCliente());
        return facturaRepository.save(facturaExistente);
    }).orElseThrow(() -> new RuntimeException("Factura no encontrada con id: " + id));
}


public Factura findFacturaById(int id) {
    return facturaRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Factura no encontrada con id: " + id));
}


public Factura anularFactura(int id) {
    return facturaRepository.findById(id)
        .map(factura -> {
            factura.setEstado("ANULADA");
            return facturaRepository.save(factura);
        })
        .orElseThrow(() -> new RuntimeException("Factura no encontrada con ID: " + id));
}



    
}